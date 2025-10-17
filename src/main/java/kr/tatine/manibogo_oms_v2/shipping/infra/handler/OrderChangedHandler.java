package kr.tatine.manibogo_oms_v2.shipping.infra.handler;

import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingOrderChangedEvent;
import kr.tatine.manibogo_oms_v2.shipping.query.UpdateShippingOrderAggService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("ShippingOrderChangedHandler")
@RequiredArgsConstructor
public class OrderChangedHandler {

    private final UpdateShippingOrderAggService updateShippingOrderAggService;

    @TransactionalEventListener(
            value = ShippingOrderChangedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handleShippingOrderChangedEvent(ShippingOrderChangedEvent shippingOrderChangedEvent) {
        updateShippingOrderAggService.update(shippingOrderChangedEvent.getShippingNumber());
    }

}
