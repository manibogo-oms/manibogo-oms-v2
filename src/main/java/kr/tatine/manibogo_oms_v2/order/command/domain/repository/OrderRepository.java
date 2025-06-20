package kr.tatine.manibogo_oms_v2.order.command.domain.repository;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, OrderNumber> { }
