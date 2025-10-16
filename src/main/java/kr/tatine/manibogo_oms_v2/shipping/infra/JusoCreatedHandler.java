package kr.tatine.manibogo_oms_v2.shipping.infra;

import kr.tatine.manibogo_oms_v2.juso.domain.JusoCreatedEvent;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.SyncShippingJusoCommand;
import kr.tatine.manibogo_oms_v2.shipping.query.port.in.SyncShippingJusoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("ShippingJusoCreatedHandler")
@RequiredArgsConstructor
public class JusoCreatedHandler {

    private final SyncShippingJusoUseCase syncShippingJusoUseCase;

    @Async
    @TransactionalEventListener(
            value = JusoCreatedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    public void handle(JusoCreatedEvent event) {

        final SyncShippingJusoCommand command = new SyncShippingJusoCommand(
                event.getJusoCode(), event.getAddress(), event.getAdmCode(), event.getSido(), event.getSigungu());

        syncShippingJusoUseCase.synchronize(command);
    }

}
