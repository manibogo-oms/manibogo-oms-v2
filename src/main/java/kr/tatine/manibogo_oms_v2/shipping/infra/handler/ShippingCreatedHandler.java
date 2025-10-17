package kr.tatine.manibogo_oms_v2.shipping.infra.handler;

import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingCreatedEvent;
import kr.tatine.manibogo_oms_v2.shipping.query.CreateShippingJusoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ShippingCreatedHandler {

    private final CreateShippingJusoService createService;

    @Async
    @TransactionalEventListener(
            value = ShippingCreatedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handle(ShippingCreatedEvent event) {
        createService.create(event.getShippingNumber());
    }

}
