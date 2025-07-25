package kr.tatine.manibogo_oms_v2.order.command.domain.repository;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.Order;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingBundleNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, OrderNumber> {

    @Query("SELECT o FROM Order o WHERE o.shippingBundleNumber = :shippingBundleNumber")
    List<Order> findByShippingBundleNumber(ShippingBundleNumber shippingBundleNumber);

}
