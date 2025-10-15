package kr.tatine.manibogo_oms_v2.juso.application.port.out;

import kr.tatine.manibogo_oms_v2.juso.domain.JusoSync;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Optional;

public interface JusoSyncQueryPort extends Repository<JusoSync, Long> {

    @Query("SELECT MAX(ji.referenceDate) FROM JusoSync ji")
    Optional<LocalDate> getLastSyncDate();



}
