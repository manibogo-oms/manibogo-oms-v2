package kr.tatine.manibogo_oms_v2.shipping.infra;

import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingOrderChangedEvent;
import kr.tatine.manibogo_oms_v2.shipping.query.UpdateShippingOrderAggService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ShippingOrderChangedHandler {

    private final UpdateShippingOrderAggService updateShippingOrderAggService;

    @TransactionalEventListener(
            value = ShippingOrderChangedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handleShippingOrderChangedEvent(ShippingOrderChangedEvent shippingOrderChangedEvent) {
        updateShippingOrderAggService.update(shippingOrderChangedEvent.getShippingNumber());
    }

}
