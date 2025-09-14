package kr.tatine.manibogo_oms_v2.shipping.query.dto;


import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingState;

public record ShippingView(
        ShippingNumber shippingNumber,
        ShippingState shippingState,
        String address1,
        String address2,
        String zipCode,
        String sido,
        String sigungu,
        String recipientName,
        String recipientTel1,
        String recipientTel2
) {

}
