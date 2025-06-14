package kr.tatine.manibogo_oms_v2.fulfillment.infra;

import kr.tatine.manibogo_oms_v2.fulfillment.command.application.CreateItemOrderHistoryService;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.ItemOrderHistoryCommand;
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

    private final CreateItemOrderHistoryService createItemOrderHistoryService;

    @Async
    @TransactionalEventListener(
            classes = ItemOrderPlacedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handleItemOrderPlaced(ItemOrderPlacedEvent event) {
        log.debug("[ItemOrderPlacedHandler.handleItemOrderPlaced] event = {}", event);

        final ItemOrderHistoryCommand command = new ItemOrderHistoryCommand(
                event.getItemOrderNumber(),
                null,
                "PLACED",
                event.getItemOrderPlacedAt()
        );

        createItemOrderHistoryService.create(command);
    }
}
