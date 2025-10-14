package kr.tatine.manibogo_oms_v2.location.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IntegrateJusoScheduler {

    private final IntegrateJusoService service;

    @Scheduled(cron = "0 0 8 * * *")
    public void scheduleIntegrateJuso() {
        service.integrate();
    }

}
