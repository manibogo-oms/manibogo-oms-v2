package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingJusoView;
import org.springframework.data.repository.Repository;

public interface ShippingJusoStorePort extends Repository<ShippingJusoView, ShippingNumber> {

    ShippingJusoView save(ShippingJusoView shippingJusoView);

}
