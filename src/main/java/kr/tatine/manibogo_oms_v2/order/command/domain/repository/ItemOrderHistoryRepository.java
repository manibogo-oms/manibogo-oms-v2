package kr.tatine.manibogo_oms_v2.order.command.domain.repository;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.ItemOrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderHistoryRepository extends JpaRepository<ItemOrderHistory, Long> {
}
