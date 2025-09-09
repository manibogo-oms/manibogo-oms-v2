package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;

import java.util.List;
import java.util.Optional;

public interface ShippingOrderViewDao {

    List<ShippingOrderView> findAllByShippingNumber(ShippingNumber shippingNumber);

}
