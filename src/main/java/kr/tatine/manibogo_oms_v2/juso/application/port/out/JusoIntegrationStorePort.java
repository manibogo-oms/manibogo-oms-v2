package kr.tatine.manibogo_oms_v2.juso.application.port.out;

import kr.tatine.manibogo_oms_v2.juso.domain.JusoIntegration;
import org.springframework.data.repository.Repository;


public interface JusoIntegrationStorePort extends Repository<JusoIntegration, Long> {

    JusoIntegration save(JusoIntegration jusoIntegration);

}
