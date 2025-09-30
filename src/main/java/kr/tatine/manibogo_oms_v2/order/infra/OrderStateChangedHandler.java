package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderStateChangedEvent;
import kr.tatine.manibogo_oms_v2.order.query.port.in.OrderStateHistoryDao;
import kr.tatine.manibogo_oms_v2.order.query.entity.OrderStateHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class OrderStateChangedHandler {

    private final OrderStateHistoryDao orderStateHistoryDao;

    @TransactionalEventListener(
            classes = OrderStateChangedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleItemOrderStateChanged(OrderStateChangedEvent event) {
        final OrderStateHistory orderStateHistory = orderStateHistoryDao.findById(event.getOrderNumber())
                .orElseGet(() -> orderStateHistoryDao.save(new OrderStateHistory(event.getOrderNumber(), event.getChangedAt())));

        orderStateHistory.changeHistory(event.getOrderState(), event.getChangedAt());
    }

}
