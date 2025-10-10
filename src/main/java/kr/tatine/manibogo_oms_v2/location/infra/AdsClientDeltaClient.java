package kr.tatine.manibogo_oms_v2.location.infra;

import jakarta.annotation.Nullable;
import kr.go.ads.client.ADSReceiver;
import kr.go.ads.client.ADSUtils;
import kr.go.ads.client.ReceiveData;
import kr.go.ads.client.ReceiveDatas;
import kr.tatine.manibogo_oms_v2.location.domain.juso.dto.JusoDelta;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoDeltaPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class AdsClientDeltaClient implements JusoDeltaPort {

    private static final ADSReceiver RECEIVER = new ADSReceiver();

    private final String MIGRATION_KEY;

    public AdsClientDeltaClient(@Value("${juso.migration.key}") String migrationKey) {
        this.MIGRATION_KEY = migrationKey;
    }

    @Override
    public JusoDelta fetch(LocalDate lastIntegratedOn) {

        try {
            final ReceiveDatas receiveDatas = requestDelta(lastIntegratedOn);

            if(receiveDatas.getResult() != 0){
                return new JusoDelta(receiveDatas.getResCode(), receiveDatas.getResMsg(), List.of());
            }

            for (final ReceiveData receiveData :
                    receiveDatas.getReceiveDatas(ADSUtils.UPDATE_ASC)) {

                if (!"P0000".equals(receiveData.getResCode())) {
                    // 해당 파일응답 에러. 특히 E1001 인경우, 해당 파일을 아직 생성하지 못한 응답으로 추후 재시도 필요.
                    System.out.println("해당파일에 대한 응답이 정상이 아니기에 재 요청 필요");
                }
            }

            return new JusoDelta(receiveDatas.getResCode(), receiveDatas.getResMsg(), List.of());

        } catch (Exception exception) {
            return new JusoDelta("E1999", exception.getMessage(), List.of());
        }
    }

    private ReceiveDatas requestDelta(LocalDate lastIntegratedOn) throws Exception {
        return RECEIVER.receiveAddr(
                MIGRATION_KEY, "D", "100001", "Y", formatDate(lastIntegratedOn), null);
    }

    @Nullable
    private static String formatDate(LocalDate localDate) {
        return Optional.ofNullable(localDate)
                .map(e -> e.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .orElse(null);
    }

}
