package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.juso.domain.JusoCreatedEvent;
import kr.tatine.manibogo_oms_v2.order.query.dto.in.SyncOrderJusoCommand;
import kr.tatine.manibogo_oms_v2.order.query.port.in.SyncOrderJusoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("OrderJusoCreatedHandler")
@RequiredArgsConstructor
public class JusoCreatedHandler {

    private final SyncOrderJusoUseCase syncOrderJusoUseCase;

    @Async
    @TransactionalEventListener(
            value = JusoCreatedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    public void handle(JusoCreatedEvent event) {

        final SyncOrderJusoCommand command =
                new SyncOrderJusoCommand(event.getJusoCode(), event.getSido());

        syncOrderJusoUseCase.synchronize(command);
    }

}
