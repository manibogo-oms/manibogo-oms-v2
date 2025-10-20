package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingJuso;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ShippingJusoQueryPort extends Repository<ShippingJuso, ShippingNumber> {

    Optional<ShippingJuso> findByShippingNumber(ShippingNumber shippingNumber);

    List<ShippingJuso> findAllByJusoCode(JusoCode jusoCode);

}
