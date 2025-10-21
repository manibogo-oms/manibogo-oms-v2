package kr.tatine.manibogo_oms_v2.shipping.query.dto.out;

import kr.tatine.manibogo_oms_v2.common.model.*;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingState;

public record ShippingView(
        ShippingNumber shippingNumber,
        ShippingMethod shippingMethod,
        ChargeType chargeType,
        ShippingState shippingState,
        OrderNumber primaryOrderNumber,
        OrderState primaryOrderState,
        String primaryOrderProduct,
        Integer primaryOrderQuantity,
        Integer totalOrderCount,
        Integer totalOrderQuantity,
        String sido,
        String sigungu,
        String address1,
        String address2,
        String zipCode,
        String recipientName,
        String recipientTel1,
        String recipientTel2,
        String customerMessage
) { }
