package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, OrderNumber> { }
