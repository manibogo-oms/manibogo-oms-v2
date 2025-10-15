package kr.tatine.manibogo_oms_v2.juso.application.service;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.common.event.Events;
import kr.tatine.manibogo_oms_v2.juso.domain.Juso;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoSync;
import kr.tatine.manibogo_oms_v2.juso.application.dto.out.JusoDelta;
import kr.tatine.manibogo_oms_v2.juso.application.port.in.SyncJusoUseCase;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.JusoDeltaPort;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.JusoSyncQueryPort;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.JusoSyncStorePort;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.JusoStorePort;
import kr.tatine.manibogo_oms_v2.juso.application.dto.out.JusoSyncedEvent;
import kr.tatine.manibogo_oms_v2.juso.domain.LastSyncNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SyncJusoService implements SyncJusoUseCase {

    private final JusoSyncQueryPort syncQueryPort;

    private final JusoSyncStorePort syncStorePort;

    private final JusoDeltaPort deltaPort;

    private final JusoStorePort storePort;

    @Override
    @Transactional
    public void synchronize() {

        final LocalDateTime lastSyncTime = syncQueryPort
                .getLastSyncDate()
                .orElseThrow(LastSyncNotFoundException::new);

        final JusoDelta delta = deltaPort.fetch(lastSyncTime.toLocalDate());

        final JusoSync sync = new JusoSync(lastSyncTime, delta.code(), delta.message());

        final List<Juso> jusos = delta.result().stream()
                .map(SyncJusoService::convert)
                .toList();

        syncStorePort.save(sync);
        storePort.saveAll(jusos);

        jusos.forEach(juso -> Events.raise(new JusoSyncedEvent(juso.getCode(), lastSyncTime)));
    }

    private static Juso convert(JusoView view) {
        return new Juso(view.jusoCode(), view.admCode(), view.address(), view.sido(), view.sigungu());
    }

}
