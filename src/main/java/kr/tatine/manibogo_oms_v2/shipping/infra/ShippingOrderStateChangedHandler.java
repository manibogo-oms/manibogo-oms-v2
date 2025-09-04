package kr.tatine.manibogo_oms_v2.shipping.infra;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderStateChangedEvent;
import kr.tatine.manibogo_oms_v2.shipping.command.application.UpdateShippingOrderCommand;
import kr.tatine.manibogo_oms_v2.shipping.command.application.UpdateShippingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ShippingOrderStateChangedHandler {

    private final UpdateShippingOrderService updateShippingOrderService;

    @TransactionalEventListener(
            value = OrderStateChangedEvent.class,
            phase = TransactionPhase.BEFORE_COMMIT
    )
    public void handleOrderStateChanged(OrderStateChangedEvent event) {
        updateShippingOrderService.update(new UpdateShippingOrderCommand(new OrderNumber(event.getOrderNumber()), OrderState.valueOf(event.getOrderState())));

    }
}
