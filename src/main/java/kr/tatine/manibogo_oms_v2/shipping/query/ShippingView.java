package kr.tatine.manibogo_oms_v2.shipping.query;


import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingState;

public record ShippingView(
        String shippingNumber,
        String shippingState,
        String address1,
        String address2,
        String zipCode,
        String recipientName,
        String recipientTel1,
        String recipientTel2
) {

}
