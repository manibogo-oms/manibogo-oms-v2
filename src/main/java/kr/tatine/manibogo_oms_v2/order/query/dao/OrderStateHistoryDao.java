package kr.tatine.manibogo_oms_v2.order.query.dao;

import kr.tatine.manibogo_oms_v2.order.query.dto.OrderStateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStateHistoryDao extends JpaRepository<OrderStateHistory, String> { }
