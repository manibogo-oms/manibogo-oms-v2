package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.repository;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.Order;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.OrderNumber;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, OrderNumber> { }
