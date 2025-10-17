package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderStateChangedEvent;
import kr.tatine.manibogo_oms_v2.order.query.entity.OrderStateHistory;
import kr.tatine.manibogo_oms_v2.order.query.port.out.OrderStateHistoryQueryPort;
import kr.tatine.manibogo_oms_v2.order.query.port.out.OrderStateHistoryStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class OrderStateChangedHandler {

    private final OrderStateHistoryQueryPort queryPort;

    private final OrderStateHistoryStorePort storePort;

    @TransactionalEventListener(
            classes = OrderStateChangedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleItemOrderStateChanged(OrderStateChangedEvent event) {
        final OrderNumber orderNumber = new OrderNumber(event.getOrderNumber());

        final OrderStateHistory orderStateHistory = queryPort.findByOrderNumber(orderNumber)
                .orElseGet(() -> storePort.save(new OrderStateHistory(orderNumber, event.getChangedAt())));

        orderStateHistory.changeHistory(event.getOrderState(), event.getChangedAt());
    }

}
