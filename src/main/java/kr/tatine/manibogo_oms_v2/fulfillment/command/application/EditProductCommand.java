package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditProductCommand(
        @NotBlank(message = "{notBlank.externalOrder.productNumber}") String productNumber,
        @NotBlank(message = "{notBlank.externalOrder.productName}") String productName,
        @NotNull(message = "{notNull.product.priority}") Integer priority,
        @NotNull(message = "{notNull.product.isEnabled}") Boolean isEnabled
) { }
