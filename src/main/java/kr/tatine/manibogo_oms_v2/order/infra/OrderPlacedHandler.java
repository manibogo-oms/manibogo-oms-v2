package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderPlacedEvent;
import kr.tatine.manibogo_oms_v2.order.query.dao.OrderStateHistoryDao;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderStateHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderPlacedHandler {

    private final OrderStateHistoryDao orderStateHistoryDao;

    @TransactionalEventListener(
            classes = OrderPlacedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleOrderPlaced(OrderPlacedEvent orderPlacedEvent) {
        final OrderStateHistory orderStateHistory = new OrderStateHistory(
                orderPlacedEvent.getOrderNumber(), orderPlacedEvent.getOrderPlacedAt());

        orderStateHistoryDao.save(orderStateHistory);
    }

}
