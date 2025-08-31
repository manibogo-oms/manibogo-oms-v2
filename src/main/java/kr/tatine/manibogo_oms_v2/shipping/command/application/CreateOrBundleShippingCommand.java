package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.Recipient;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;

public record CreateOrBundleShippingCommand(
        ShippingNumber shippingNumber,
        ShippingMethod shippingMethod,
        ChargeType chargeType,
        Recipient recipient
) {}
