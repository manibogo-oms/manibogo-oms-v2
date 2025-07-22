package kr.tatine.manibogo_oms_v2.product.command.application;

public record EditProductCommand(
        String productNumber,
        String productName,
        Integer priority,
        Boolean isEnabled
) { }
