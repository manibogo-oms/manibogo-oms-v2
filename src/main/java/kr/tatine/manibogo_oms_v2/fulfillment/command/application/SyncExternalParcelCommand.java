package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.constraints.NotBlank;

public record SyncExternalParcelCommand(
        @NotBlank(message = "{notBlank.externalOrder.itemOrderNumber}")
        String itemOrderNumber,

        @NotBlank(message = "{notBlank.shippingTrackingNumber}")
        String shippingTrackingNumber,

        @NotBlank(message = "{notBlank.shippingCompanyName}")
        String shippingCompanyName
) { }
