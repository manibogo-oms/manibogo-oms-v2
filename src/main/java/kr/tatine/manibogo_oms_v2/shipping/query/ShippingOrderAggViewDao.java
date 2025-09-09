package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ShippingOrderAggViewDao extends Repository<ShippingOrderAggView, ShippingNumber> {

    ShippingOrderAggView save(ShippingOrderAggView shippingOrderAggView);

}
