package kr.tatine.manibogo_oms_v2.order.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.location.domain.address.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface OrderAddressQueryPort extends Repository<Order, OrderNumber> {

    @Query("SELECT o.recipient.address FROM Order o WHERE o.number = :number")
    Optional<Address> findByNumber(OrderNumber number);

}
