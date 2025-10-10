package kr.tatine.manibogo_oms_v2.location.domain.juso.port.out;

import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoIntegration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Optional;

public interface JusoIntegrationQueryPort extends Repository<JusoIntegration, Long> {

    @Query("SELECT MAX(ji.integratedOn) FROM JusoIntegration ji")
    Optional<LocalDate> getLastIntegratedOn();


}
