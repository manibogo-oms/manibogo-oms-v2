package kr.tatine.manibogo_oms_v2.order.command.domain.repository;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {
}
