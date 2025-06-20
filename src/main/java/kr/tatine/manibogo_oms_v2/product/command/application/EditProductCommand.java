package kr.tatine.manibogo_oms_v2.product.command.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditProductCommand(
        String productNumber,
        String productName,
        Integer priority,
        Boolean isEnabled
) { }
