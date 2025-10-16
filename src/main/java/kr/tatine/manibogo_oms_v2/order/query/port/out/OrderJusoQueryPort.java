package kr.tatine.manibogo_oms_v2.order.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;
import kr.tatine.manibogo_oms_v2.order.query.entity.OrderJuso;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface OrderJusoQueryPort extends Repository<OrderJuso, OrderNumber> {

    List<OrderJuso> findAllByJusoCode(JusoCode jusoCode);

}
