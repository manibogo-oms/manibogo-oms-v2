package kr.tatine.manibogo_oms_v2.location.infra;

import kr.tatine.manibogo_oms_v2.location.domain.juso.port.in.IntegrateJusoUseCase;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoDeltaPort;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoIntegrationQueryPort;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class IntegrateJusoService implements IntegrateJusoUseCase {

    private final JusoIntegrationQueryPort integrationQueryPort;

    private final JusoDeltaPort deltaPort;

    private final JusoStorePort storePort;

    @Override
    public void integrate() {

        final LocalDate lastIntegratedOn =
                integrationQueryPort.getLastIntegratedOn().orElse(null);

        deltaPort.fetch(lastIntegratedOn);
    }
}
