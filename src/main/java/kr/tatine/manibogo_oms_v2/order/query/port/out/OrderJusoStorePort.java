package kr.tatine.manibogo_oms_v2.order.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.query.entity.OrderJuso;
import org.springframework.data.repository.Repository;

public interface OrderJusoStorePort extends Repository<OrderJuso, OrderNumber> {

    OrderJuso save(OrderJuso juso);

}
