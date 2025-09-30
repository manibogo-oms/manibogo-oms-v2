package kr.tatine.manibogo_oms_v2.order.query.port.in;

import kr.tatine.manibogo_oms_v2.order.query.entity.OrderStateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStateHistoryDao extends JpaRepository<OrderStateHistory, String> { }
