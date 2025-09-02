package kr.tatine.manibogo_oms_v2.shipping.infra;

import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderShippingInfoChangedEvent;
import kr.tatine.manibogo_oms_v2.shipping.command.application.CreateOrBundleShippingCommand;
import kr.tatine.manibogo_oms_v2.shipping.command.application.CreateOrBundleShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderShippingInfoChangedHandler {

    private final CreateOrBundleShippingService createOrBundleShippingService;

    @TransactionalEventListener(
            value = OrderShippingInfoChangedEvent.class,
            phase = TransactionPhase.BEFORE_COMMIT
    )
    public void handleOrderShippingInfoChanged(final OrderShippingInfoChangedEvent event) {

        SecurityContextHolder.getContext().getAuthentication();

        final CreateOrBundleShippingCommand command = new CreateOrBundleShippingCommand(event.getShippingNumber(), event.getMethod(), event.getChargeType(), event.getRecipient());

        createOrBundleShippingService.createOrBundle(command);
    }


}
