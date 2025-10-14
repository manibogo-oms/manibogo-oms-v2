package kr.tatine.manibogo_oms_v2.location.infra;

import jakarta.annotation.Nullable;
import kr.go.ads.client.ADSReceiver;
import kr.go.ads.client.ADSUtils;
import kr.go.ads.client.ReceiveData;
import kr.go.ads.client.ReceiveDatas;
import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoCode;
import kr.tatine.manibogo_oms_v2.location.domain.juso.dto.JusoDelta;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoDeltaPort;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.internal.xml.CloseIgnoringInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class AdsClientDeltaClient implements JusoDeltaPort {

    private static final ADSReceiver RECEIVER = new ADSReceiver();

    private final String migrationKey;

    public AdsClientDeltaClient(@Value("${juso.migration.key}") String migrationKey,
                                @Value("${juso.migration.data-path}") String dataPath) {
        this.migrationKey = migrationKey;

        RECEIVER.setFilePath(dataPath);
        RECEIVER.setCreateDateDirectory(ADSUtils.YYMMDD);
    }

    @Override
    public JusoDelta fetch(LocalDate lastIntegratedOn) {

        try {
            final ReceiveDatas receiveDatas = requestDelta(lastIntegratedOn);

            if(receiveDatas.getResult() != 0){
                return new JusoDelta(receiveDatas.getResCode(), receiveDatas.getResMsg(), List.of());
            }


            final List<JusoView> deltaData = new ArrayList<>();

            for (final ReceiveData receiveData :
                    receiveDatas.getReceiveDatas(ADSUtils.UPDATE_ASC)) {

                try (final ZipInputStream zipInputStream =
                             new ZipInputStream(new FileInputStream(receiveData.getFilePath()))) {

                    ZipEntry zipEntry;

                    while ((zipEntry = zipInputStream.getNextEntry()) != null) {

                        if (zipEntry.isDirectory()) continue;
                        if (!zipEntry.getName().toLowerCase().endsWith("mst.txt")) continue;

                        try (final BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(new CloseIgnoringInputStream(zipInputStream), "EUC-KR"))) {

                            bufferedReader.lines()
                                    .map(AdsClientDeltaClient::deserialize)
                                    .forEach(juso -> juso.ifPresent(deltaData::add));

                        } catch (IOException ioException) {
                            return new JusoDelta("E1999", "데이터 파일 파싱 중 오료가 발생했습니다: " + ioException.getMessage(), deltaData);
                        }

                        zipInputStream.closeEntry();
                    }
                } catch (IOException ioException) {
                    return new JusoDelta("E1999", "압축 파일 파싱 중 오료가 발생했습니다: " + ioException.getMessage(), deltaData);
                }
            }

            return new JusoDelta(receiveDatas.getResCode(), receiveDatas.getResMsg(), deltaData);

        } catch (Exception exception) {
            return new JusoDelta("E1999", exception.getMessage(), List.of());
        }
    }

    private ReceiveDatas requestDelta(LocalDate lastIntegratedOn) throws Exception {
        return RECEIVER.receiveAddr(
                migrationKey, "D", "100001", "Y", formatDate(lastIntegratedOn), null);
    }

    private static Optional<JusoView> deserialize(@Nullable String line) {

        if (Strings.isBlank(line) || line.equalsIgnoreCase("No Data")) return Optional.empty();

        final String[] tokens = line.split("\\|", -1);

        String address = "%s %s %s %s %s".formatted(tokens[2], tokens[3], tokens[4], tokens[10], tokens[12]);

        if (!Objects.equals(tokens[13], "0")) {
            address += "-%s".formatted(tokens[13]);
        }

        return Optional.of(
                new JusoView(new JusoCode(tokens[0]), tokens[1], address, tokens[2], tokens[3]));
    }

    @Nullable
    private static String formatDate(LocalDate localDate) {
        return Optional.ofNullable(localDate)
                .map(e -> e.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .orElse(null);
    }
}
