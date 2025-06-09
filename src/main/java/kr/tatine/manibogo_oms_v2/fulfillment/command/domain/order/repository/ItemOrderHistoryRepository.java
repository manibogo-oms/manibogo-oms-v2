package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.ItemOrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderHistoryRepository extends JpaRepository<ItemOrderHistory, Long> {
}
