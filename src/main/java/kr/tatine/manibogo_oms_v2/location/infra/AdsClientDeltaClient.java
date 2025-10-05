package kr.tatine.manibogo_oms_v2.location.infra;

import kr.tatine.manibogo_oms_v2.location.domain.juso.Juso;
import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoDeltaPort;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

public class AdsClientDeltaClient implements JusoDeltaPort {

//    private static final ADSReceiver RECEIVER = new ADSReceiver();

    private final String MIGRATION_KEY;

    public AdsClientDeltaClient(@Value("${juso.key}") String migrationKey) {
        this.MIGRATION_KEY = migrationKey;
    }

    @Override
    public List<Juso> fetch(LocalDate from) {

//        RECEIVER.receiveAddr(MIGRATION_KEY, )
        return List.of();
    }
}
