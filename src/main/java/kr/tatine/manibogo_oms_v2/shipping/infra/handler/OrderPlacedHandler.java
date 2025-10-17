package kr.tatine.manibogo_oms_v2.shipping.infra.handler;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderPlacedEvent;
import kr.tatine.manibogo_oms_v2.shipping.command.application.CreateOrBundleShippingCommand;
import kr.tatine.manibogo_oms_v2.shipping.command.application.CreateOrBundleShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("ShippingOrderPlacedHandler")
@RequiredArgsConstructor
public class OrderPlacedHandler {

    private final CreateOrBundleShippingService createOrBundleShippingService;

    @TransactionalEventListener(
            value = OrderPlacedEvent.class,
            phase = TransactionPhase.BEFORE_COMMIT
    )
    public void handleOrderPlaced(OrderPlacedEvent event) {
        createOrBundleShippingService.createOrBundle(new CreateOrBundleShippingCommand(new OrderNumber(event.getOrderNumber())));
    }
}
