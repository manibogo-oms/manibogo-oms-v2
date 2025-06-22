package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.Validator;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderCommandValidator {

    private PlaceOrderCommandValidator() {}

    public static List<ValidationError> validate(PlaceOrderCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (command.customerName() == null || command.customerName().isBlank()) {
            errors.add(ValidationError.of("customerName", "required.order.customerName"));
        }

        if (command.customerTel() == null || command.customerTel().isBlank()) {
            errors.add(ValidationError.of("customerTel", "required.order.customerTel"));
        } else if (Validator.isInvalidPhoneNumber(command.customerTel())) {
            errors.add(ValidationError.of("customerTel", "invalid.order.customerTel"));
        }

        if (command.recipientName() == null || command.recipientName().isBlank()) {
            errors.add(ValidationError.of("recipientName", "required.order.recipientName"));
        }

        if (command.recipientTel1() == null || command.recipientTel1().isBlank()) {
            errors.add(ValidationError.of("recipientTel1", "required.order.recipientTel1"));
        } else if (Validator.isInvalidPhoneNumber(command.recipientTel1())) {
            errors.add(ValidationError.of("recipientTel1", "invalid.order.recipientTel1"));
        }

        if (command.recipientTel2() != null && Validator.isInvalidPhoneNumber(command.recipientTel2())) {
            errors.add(ValidationError.of("recipientTel2", "invalid.order.recipientTel2"));
        }

        if ((command.recipientZipCode() == null || command.recipientZipCode().isBlank())
                || (command.recipientAddress1() == null || command.recipientAddress1().isBlank())) {
            errors.add(ValidationError.of("recipientAddress", "required.order.recipientAddress"));
        }

        if (command.productNumber() == null || command.productNumber().isBlank()) {
            errors.add(ValidationError.of("productNumber", "required.order.productNumber"));
        }

        if (command.amount() == null) {
            errors.add(ValidationError.of("amount", "required.order.amount"));
        } else if (command.amount() < 0) {
            errors.add(ValidationError.of("amount", "min.order.amount"));
        }

        if (command.totalPrice() == null) {
            errors.add(ValidationError.of("totalPrice", "required.order.totalPrice"));
        } else if (command.totalPrice() < 0) {
            errors.add(ValidationError.of("totalPrice", "min.order.totalPrice"));
        }

        if (command.options() != null) {
            for (int i = 0; i < command.options().size(); i ++) {
                final PlaceOrderCommand.PlaceOrderOptionCommand option = command.options().get(i);
                final String fieldName = "options[%d].value".formatted(i);

                if (option.key() == null || option.key().isBlank()) {
                    errors.add(ValidationError.of(fieldName, "required.order." + fieldName));
                }

                if (option.value() == null || option.value().isBlank()) {
                    errors.add(ValidationError.of(fieldName, "required.order." + fieldName));
                }
            }
        }

        if (command.shippingMethod() == null) {
            errors.add(ValidationError.of("shippingMethod", "required.order.shippingMethod"));
        }

        if (command.shippingChargeType() == null) {
            errors.add(ValidationError.of("shippingChargeType", "required.order.shippingChargeType"));
        }

        if (command.dispatchDeadline() == null) {
            errors.add(ValidationError.of("dispatchDeadline", "required.order.dispatchDeadline"));
        }

        return errors;
    }

}
