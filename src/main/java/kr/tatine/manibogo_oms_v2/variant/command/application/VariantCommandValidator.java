package kr.tatine.manibogo_oms_v2.variant.command.application;

import kr.tatine.manibogo_oms_v2.common.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class VariantCommandValidator {

    private VariantCommandValidator() {}

    public static List<ValidationError> validate(VariantCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (command.productNumber() == null || command.productNumber().isBlank()) {
            errors.add(ValidationError.of("productNumber", "notBlank.variant.productNumber"));
        }

        if (command.key() == null || command.key().isBlank()) {
            errors.add(ValidationError.of("key", "notBlank.variant.key"));
        }

        if (command.value() == null || command.value().isBlank()) {
            errors.add(ValidationError.of("value", "notBlank.variant.value"));
        }

        if (command.label() == null || command.label().isBlank()) {
            errors.add(ValidationError.of("label", "notBlank.variant.label"));
        }

        return errors;
    }

}
