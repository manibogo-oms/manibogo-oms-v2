package kr.tatine.manibogo_oms_v2.location.infra;

import kr.tatine.manibogo_oms_v2.location.domain.juso.Juso;
import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoQueryPort;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.Repository;

@Order(1)
public interface JusoDao extends JusoQueryPort, Repository<Juso, String> {}
