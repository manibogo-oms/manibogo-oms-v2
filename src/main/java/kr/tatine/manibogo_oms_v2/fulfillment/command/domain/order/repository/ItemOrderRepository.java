package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.ItemOrder;
import org.springframework.data.repository.CrudRepository;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, ItemOrderNumber> { }
