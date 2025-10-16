package kr.tatine.manibogo_oms_v2.order.query;

import kr.tatine.manibogo_oms_v2.order.query.dto.in.SyncOrderJusoCommand;
import kr.tatine.manibogo_oms_v2.order.query.entity.OrderJuso;
import kr.tatine.manibogo_oms_v2.order.query.port.in.SyncOrderJusoUseCase;
import kr.tatine.manibogo_oms_v2.order.query.port.out.OrderJusoQueryPort;
import kr.tatine.manibogo_oms_v2.order.query.port.out.OrderJusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SyncOrderJusoService implements SyncOrderJusoUseCase {

    private final OrderJusoQueryPort orderJusoQueryPort;

    private final OrderJusoStorePort orderJusoStorePort;

    @Override
    @Transactional
    public void synchronize(SyncOrderJusoCommand command) {

        final List<OrderJuso> orderJusos =
                orderJusoQueryPort.findAllByJusoCode(command.jusoCode());

        if (orderJusos.isEmpty()) return;

        orderJusos.forEach(orderJuso -> orderJuso.updateJuso(command.sido()));

        orderJusoStorePort.saveAll(orderJusos);
    }
}
