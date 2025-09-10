package kr.tatine.manibogo_oms_v2.shipping.query;

public record ShippingPageView(
        String shippingNumber,
        String shippingState,
        String primaryOrderNumber,
        String primaryOrderState,
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
