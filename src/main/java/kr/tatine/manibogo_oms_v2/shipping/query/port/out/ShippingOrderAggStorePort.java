package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingOrderAggView;
import org.springframework.data.repository.Repository;

public interface ShippingOrderAggStorePort extends Repository<ShippingOrderAggView, String> {

    ShippingOrderAggView save(ShippingOrderAggView shippingOrderAggView);

}
