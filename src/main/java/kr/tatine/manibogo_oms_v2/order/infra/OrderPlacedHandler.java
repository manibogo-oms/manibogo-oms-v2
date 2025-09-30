package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderPlacedEvent;
import kr.tatine.manibogo_oms_v2.order.query.port.in.OrderJusoUpsertUseCase;
import kr.tatine.manibogo_oms_v2.order.query.port.in.OrderStateHistoryCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderPlacedHandler {

    private final OrderStateHistoryCreateUseCase historyCreateUseCase;

    private final OrderJusoUpsertUseCase orderJusoUpsertUseCase;

    @TransactionalEventListener(
            classes = OrderPlacedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleOrderPlaced(OrderPlacedEvent orderPlacedEvent) {
        final OrderNumber orderNumber = new OrderNumber(orderPlacedEvent.getOrderNumber());

        orderJusoUpsertUseCase.upsert(orderNumber);
        historyCreateUseCase.create(orderNumber, orderPlacedEvent.getOrderPlacedAt());
    }

}
