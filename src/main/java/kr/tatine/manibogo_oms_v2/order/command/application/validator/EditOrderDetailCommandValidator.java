package kr.tatine.manibogo_oms_v2.order.command.application.validator;

import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.Validator;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.EditOrderDetailCommand;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class EditOrderDetailCommandValidator {

    private EditOrderDetailCommandValidator() {}

    public static List<ValidationError> validate(EditOrderDetailCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (command.orderNumber() == null || command.orderNumber().isBlank()) {
            errors.add(ValidationError.of("orderNumber", "required.order.orderNumber"));
        }

        if (command.orderState() == null) {
            errors.add(ValidationError.of("orderState", "required.order.orderState"));
        }

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

        if (Strings.isNotBlank(command.recipientTel2()) && Validator.isInvalidPhoneNumber(command.recipientTel2())) {
            errors.add(ValidationError.of("recipientTel2", "invalid.order.recipientTel2"));
        }

        if ((command.recipientZipCode() == null || command.recipientZipCode().isBlank())
                || (command.recipientAddress1() == null || command.recipientAddress1().isBlank())) {
            errors.add(ValidationError.of("recipientAddress", "required.order.recipientAddress"));
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
