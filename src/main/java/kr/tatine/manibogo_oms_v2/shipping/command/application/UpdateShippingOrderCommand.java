package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.OrderState;

public record UpdateShippingOrderCommand(
        OrderNumber orderNumber,
        OrderState orderState
) { }
