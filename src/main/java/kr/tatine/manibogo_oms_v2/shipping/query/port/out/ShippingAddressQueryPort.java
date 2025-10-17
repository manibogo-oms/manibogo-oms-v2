package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.Address;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.Shipping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ShippingAddressQueryPort extends Repository<Shipping, ShippingNumber> {

    @Query("SELECT s.recipient.address FROM Shipping s WHERE s.number = :number")
    Optional<Address> findByNumber(ShippingNumber number);

}
