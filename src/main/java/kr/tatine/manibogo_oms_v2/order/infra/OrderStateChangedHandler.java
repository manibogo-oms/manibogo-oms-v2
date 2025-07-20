package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.order.command.application.service.CreateOrderLogService;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.CreateOrderLogCommand;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderStateChangedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderStateChangedHandler {

    private final CreateOrderLogService createOrderLogService;

    @TransactionalEventListener(
            classes = OrderStateChangedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleItemOrderStateChanged(OrderStateChangedEvent event) {
        log.debug("[temOrderStateChangedHandler.handleItemOrderStateChanged] event = {}", event);

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        createOrderLogService.create(new CreateOrderLogCommand(
                event.getOrderNumber(),
                event.getPreviousStateName(),
                event.getNewStateName(),
                event.getChangedAt(),
                authentication.getName()
        ));
    }


}
