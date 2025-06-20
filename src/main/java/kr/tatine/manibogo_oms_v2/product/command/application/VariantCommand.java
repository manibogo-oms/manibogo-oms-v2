package kr.tatine.manibogo_oms_v2.product.command.application;

public record VariantCommand(
        String productNumber,
        String key,
        String value,
        String label
) { }
