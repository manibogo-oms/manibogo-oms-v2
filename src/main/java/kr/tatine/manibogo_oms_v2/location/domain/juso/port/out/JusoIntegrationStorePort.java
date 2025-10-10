package kr.tatine.manibogo_oms_v2.location.domain.juso.port.out;

import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoIntegration;
import org.springframework.data.repository.Repository;


public interface JusoIntegrationStorePort extends Repository<JusoIntegration, Long> {

    JusoIntegration save(JusoIntegration jusoIntegration);

}
