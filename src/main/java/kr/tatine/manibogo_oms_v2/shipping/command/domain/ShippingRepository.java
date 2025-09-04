package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShippingRepository extends JpaRepository<Shipping, ShippingNumber> {

    @Query("SELECT DISTINCT s FROM Shipping s JOIN s.orders so WHERE so.orderNumber = :orderNumber")
    Optional<Shipping> findByShippingOrderNumber(OrderNumber orderNumber);

}
