package kr.tatine.manibogo_oms_v2.order.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.query.entity.OrderStateHistory;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface OrderStateHistoryQueryPort extends Repository<OrderStateHistory, String> {

    Optional<OrderStateHistory> findByOrderNumber(OrderNumber orderNumber);

}
