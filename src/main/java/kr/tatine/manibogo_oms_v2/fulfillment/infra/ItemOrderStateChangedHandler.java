package kr.tatine.manibogo_oms_v2.fulfillment.infra;

import kr.tatine.manibogo_oms_v2.fulfillment.command.application.CreateItemOrderHistoryService;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.ItemOrderHistoryCommand;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.event.ItemOrderStateChangedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemOrderStateChangedHandler {

    private final CreateItemOrderHistoryService createItemOrderHistoryService;

    @Async
    @TransactionalEventListener(
            classes = ItemOrderStateChangedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handleItemOrderStateChanged(ItemOrderStateChangedEvent event) {
        log.debug("[temOrderStateChangedHandler.handleItemOrderStateChanged] event = {}", event);

        createItemOrderHistoryService.create(new ItemOrderHistoryCommand(
                event.getItemOrderNumber(),
                event.getPreviousStateName(),
                event.getNewStateName(),
                event.getChangedAt()));
    }


}
