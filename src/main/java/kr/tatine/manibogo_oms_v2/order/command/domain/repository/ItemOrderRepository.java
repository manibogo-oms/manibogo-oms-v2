package kr.tatine.manibogo_oms_v2.order.command.domain.repository;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.ItemOrder;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderNumber;
import org.springframework.data.repository.CrudRepository;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, ItemOrderNumber> { }
