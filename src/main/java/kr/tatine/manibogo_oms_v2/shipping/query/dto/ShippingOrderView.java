package kr.tatine.manibogo_oms_v2.shipping.query.dto;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;

public record ShippingOrderView(
        ShippingNumber shippingNumber,
        OrderNumber orderNumber,
        OrderState orderState,
        String productName,
        Integer quantity
) { }
