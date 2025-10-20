package kr.tatine.manibogo_oms_v2.shipping.query.port.in;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingOrderView;

import java.util.List;

public interface QueryShippingOrderUseCase {

    List<ShippingOrderView> findAllByShippingNumber(ShippingNumber shippingNumber);

}
