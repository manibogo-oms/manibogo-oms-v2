package kr.tatine.manibogo_oms_v2.fulfillment.infra;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.event.ItemOrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemOrderPlacedHandler {

    @Async
    @TransactionalEventListener(
            classes = ItemOrderPlacedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handle(ItemOrderPlacedEvent event) {
        log.debug("[ItemOrderPlacedHandler.handle] event = {}", event);
    }

}
