package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.*;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;

public record OrderShippingView(
        OrderNumber orderNumber,
        OrderState orderState,
        ShippingNumber shippingNumber,
        ShippingMethod shippingMethod,
        ChargeType chargeType,
        Recipient recipient,
        ProductNumber productNumber,
        Integer amount
) { }
