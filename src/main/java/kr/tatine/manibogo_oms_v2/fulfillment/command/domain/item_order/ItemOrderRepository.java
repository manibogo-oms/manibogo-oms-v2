package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import org.springframework.data.repository.CrudRepository;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, ItemOrderNumber> { }
