package kr.tatine.manibogo_oms_v2.juso.infra;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.JusoQueryPort;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@Order(1)
public interface JusoDao extends JusoQueryPort, Repository<JusoView, String> {

    @Query("SELECT new kr.tatine.manibogo_oms_v2.common.contract.out.JusoView(j.code, j.admCode, j.address, j.sido, j.sigungu) FROM Juso j WHERE address = :address")
    Optional<JusoView> findByAddress(String address);

}
