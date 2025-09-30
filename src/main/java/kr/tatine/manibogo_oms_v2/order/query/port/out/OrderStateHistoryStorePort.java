package kr.tatine.manibogo_oms_v2.order.query.port.out;

import kr.tatine.manibogo_oms_v2.order.query.entity.OrderStateHistory;
import org.springframework.data.repository.Repository;

public interface OrderStateHistoryStorePort extends Repository<OrderStateHistory, String> {

    OrderStateHistory save(OrderStateHistory orderStateHistory);

}
