package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.order.command.application.CreateOrderLogService;
import kr.tatine.manibogo_oms_v2.order.command.application.CreateOrderLogCommand;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderStateChangedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderStateChangedHandler {

    private final CreateOrderLogService createOrderLogService;

    @Async
    @TransactionalEventListener(
            classes = OrderStateChangedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handleItemOrderStateChanged(OrderStateChangedEvent event) {
        log.debug("[temOrderStateChangedHandler.handleItemOrderStateChanged] event = {}", event);

        createOrderLogService.create(new CreateOrderLogCommand(
                event.getOrderNumber(),
                event.getPreviousStateName(),
                event.getNewStateName(),
                event.getChangedAt()));
    }


}
