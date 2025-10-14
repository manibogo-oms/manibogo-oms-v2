package kr.tatine.manibogo_oms_v2.juso.infra;

import kr.tatine.manibogo_oms_v2.juso.application.service.IntegrateJusoService;
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
