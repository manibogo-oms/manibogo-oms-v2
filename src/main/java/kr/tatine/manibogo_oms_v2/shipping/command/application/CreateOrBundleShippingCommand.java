package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.common.model.*;

public record CreateOrBundleShippingCommand(
        OrderNumber orderNumber,
        OrderState orderState,
        ShippingNumber shippingNumber,
        ShippingMethod shippingMethod,
        ChargeType chargeType,
        Recipient recipient
) {}
