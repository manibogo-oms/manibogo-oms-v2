package kr.tatine.manibogo_oms_v2.shipping.infra;

import kr.tatine.manibogo_oms_v2.shipping.query.entity.Juso;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.JusoQueryPort;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.Repository;

@Order(1)
public interface JusoDao extends JusoQueryPort, Repository<Juso, String> {}
