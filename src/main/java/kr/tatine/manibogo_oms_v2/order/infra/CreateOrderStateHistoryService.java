package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.query.entity.OrderStateHistory;
import kr.tatine.manibogo_oms_v2.order.query.port.in.OrderStateHistoryCreateUseCase;
import kr.tatine.manibogo_oms_v2.order.query.port.out.OrderStateHistoryStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateOrderStateHistoryService implements OrderStateHistoryCreateUseCase {

    private final OrderStateHistoryStorePort storePort;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create(OrderNumber orderNumber, LocalDateTime placedAt) {
        final OrderStateHistory orderStateHistory =
                new OrderStateHistory(orderNumber, placedAt);

        storePort.save(orderStateHistory);
    }
}
