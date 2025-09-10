package kr.tatine.manibogo_oms_v2.shipping.query.dto;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingState;

public record ShippingPageView(
        ShippingNumber shippingNumber,
        ShippingState shippingState,
        OrderNumber primaryOrderNumber,
        OrderState primaryOrderState,
        String primaryProductName,
        Integer primaryOrderQuantity,
        Integer totalOrderCount,
        Integer totalQuantity,
        String sido,
        String sigungu,
        String address1,
        String address2,
        String zipCode,
        String recipientName,
        String recipientTel1,
        String recipientTel2
) { }
