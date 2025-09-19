package kr.tatine.manibogo_oms_v2.shipping.query.dto.out;


import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingState;

public record ShippingView(
        ShippingNumber shippingNumber,
        ShippingMethod shippingMethod,
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
