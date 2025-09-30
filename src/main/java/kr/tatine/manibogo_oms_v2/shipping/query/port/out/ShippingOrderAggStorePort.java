package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingOrderAgg;
import org.springframework.data.repository.Repository;

public interface ShippingOrderAggStorePort extends Repository<ShippingOrderAgg, String> {

    ShippingOrderAgg save(ShippingOrderAgg shippingOrderAgg);

}
