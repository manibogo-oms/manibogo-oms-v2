package kr.tatine.manibogo_oms_v2.synchronize.command.application;

import jakarta.validation.constraints.NotBlank;

public record SyncOrderTrackingInfoCommand(
        @NotBlank(message = "{notBlank.externalOrder.itemOrderNumber}")
        String orderNumber,

        @NotBlank(message = "{notBlank.shippingTrackingNumber}")
        String shippingTrackingNumber,

        @NotBlank(message = "{notBlank.shippingCompanyName}")
        String shippingCompanyName
) { }
