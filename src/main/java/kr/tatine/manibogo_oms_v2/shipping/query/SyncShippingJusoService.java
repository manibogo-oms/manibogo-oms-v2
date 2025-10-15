package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.contract.out.DeltaJusoQueryPort;
import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.common.synchronize.*;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;
import kr.tatine.manibogo_oms_v2.juso.domain.LastSyncNotFoundException;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingJuso;
import kr.tatine.manibogo_oms_v2.shipping.query.port.in.SyncShippingJusoUseCase;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingJusoQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingJusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SyncShippingJusoService implements SyncShippingJusoUseCase {

    private final SyncQueryPort syncQueryPort;

    private final SyncStorePort syncStorePort;

    private final DeltaJusoQueryPort deltaJusoQueryPort;

    private final ShippingJusoQueryPort shippingJusoQueryPort;

    private final ShippingJusoStorePort shippingJusoStorePort;

    private static final SyncKey SYNC_KEY = new SyncKey("shipping_bc", "juso_bc", "juso");

    @Override
    @Transactional
    public void synchronize() {

        final Synchronize synchronize = syncQueryPort
                .findByKey(SYNC_KEY)
                .orElseGet(this::createInitialSync);

        final List<JusoView> deltaData =
                deltaJusoQueryPort.findSyncAll(synchronize.getReferenceDate());

        final Map<JusoCode, JusoView> deltaDataMap = deltaData.stream()
                .collect(Collectors.toMap(JusoView::jusoCode, Function.identity()));

        final List<JusoCode> jusoCodes = deltaData.stream().map(JusoView::jusoCode).toList();

        final List<ShippingJuso> shippingJusos = shippingJusoQueryPort.findAllByJusoCodes(jusoCodes);

        shippingJusos.forEach(shippingJuso -> {
            final JusoView jusoView = deltaDataMap.get(shippingJuso.getJusoCode());
            shippingJuso.synchronize(jusoView.address(), jusoView.admCode(), jusoView.sido(), jusoView.sigungu());
        });

        shippingJusoStorePort.saveAll(shippingJusos);


        syncStorePort.save(synchronize);
    }

    private Synchronize createInitialSync() {
        final LocalDate lastSyncDate = shippingJusoQueryPort.findLastSyncTime()
                .map(LocalDateTime::toLocalDate)
                .orElseThrow(LastSyncNotFoundException::new);

        return new Synchronize(SYNC_KEY, lastSyncDate);
    }


}
