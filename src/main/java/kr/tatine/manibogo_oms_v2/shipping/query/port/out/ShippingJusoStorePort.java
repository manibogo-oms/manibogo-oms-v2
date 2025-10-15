package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingJuso;
import org.springframework.data.repository.Repository;


public interface ShippingJusoStorePort extends Repository<ShippingJuso, ShippingNumber> {

    ShippingJuso save(ShippingJuso shippingJuso);

    void saveAll(Iterable<ShippingJuso> shippingJusos);

}
