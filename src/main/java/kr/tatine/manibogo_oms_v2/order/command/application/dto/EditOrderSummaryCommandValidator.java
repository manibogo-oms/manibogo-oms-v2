package kr.tatine.manibogo_oms_v2.order.command.application.dto;

import kr.tatine.manibogo_oms_v2.common.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class EditOrderSummaryCommandValidator {

    private EditOrderSummaryCommandValidator() {}

    public static List<ValidationError> validate(EditOrderSummaryCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (command.orderNumber() == null || command.orderNumber().isBlank()) {
            errors.add(ValidationError.of("orderNumber", "required.order.orderNumber"));
        }

        if (command.orderState() == null) {
            errors.add(ValidationError.of("orderState", "required.order.orderState"));
        }

        if (command.dispatchDeadline() == null) {
            errors.add(ValidationError.of("dispatchDeadline", "required.order.dispatchDeadline"));
        }

        return errors;
    }


}
