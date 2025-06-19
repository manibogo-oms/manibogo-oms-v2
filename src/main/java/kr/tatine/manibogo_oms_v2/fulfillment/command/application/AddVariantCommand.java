package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

public record AddVariantCommand(
        String productNumber,
        String key,
        String value,
        String label
) { }
