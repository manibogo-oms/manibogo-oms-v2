package kr.tatine.manibogo_oms_v2.shipping.command.application;

public record CreateOrBundleShippingCommand(
        String shippingNumber,
        String method,
        String chargeType,
        String recipientName,
        String recipientTel1,
        String recipientTel2,
        String address1,
        String address2,
        String zipCode
) {}
