package kr.tatine.manibogo_oms_v2.location.infra;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.location.domain.juso.Juso;
import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoIntegration;
import kr.tatine.manibogo_oms_v2.location.domain.juso.dto.JusoDelta;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.in.IntegrateJusoUseCase;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoDeltaPort;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoIntegrationQueryPort;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoIntegrationStorePort;
import kr.tatine.manibogo_oms_v2.location.domain.juso.port.out.JusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IntegrateJusoService implements IntegrateJusoUseCase {

    private final JusoIntegrationQueryPort integrationQueryPort;

    private final JusoIntegrationStorePort integrationStorePort;

    private final JusoDeltaPort deltaPort;

    private final JusoStorePort storePort;

    @Override
    @Transactional
    public void integrate() {

        final LocalDate lastIntegratedOn = integrationQueryPort
                .getLastIntegratedOn()
                .orElseThrow(LayerInstantiationException::new);

        final JusoDelta delta = deltaPort.fetch(lastIntegratedOn);

        final JusoIntegration integration =
                new JusoIntegration(lastIntegratedOn, delta.code(), delta.message());

        final List<Juso> jusos = delta.result().stream()
                .map(IntegrateJusoService::convert)
                .toList();

        integrationStorePort.save(integration);
        storePort.saveAll(jusos);
    }

    private static Juso convert(JusoView view) {
        return new Juso(view.jusoCode(), view.admCode(), view.address(), view.sido(), view.sigungu());
    }

}
