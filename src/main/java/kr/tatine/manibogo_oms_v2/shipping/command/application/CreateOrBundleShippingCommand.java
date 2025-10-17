package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;

public record CreateOrBundleShippingCommand(
        OrderNumber orderNumber
) {}
