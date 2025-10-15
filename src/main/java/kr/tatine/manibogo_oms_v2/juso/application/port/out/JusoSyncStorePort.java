package kr.tatine.manibogo_oms_v2.juso.application.port.out;

import kr.tatine.manibogo_oms_v2.juso.domain.JusoSync;
import org.springframework.data.repository.Repository;


public interface JusoSyncStorePort extends Repository<JusoSync, Long> {

    JusoSync save(JusoSync jusoSync);

}
