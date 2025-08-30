package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.Validator;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateOrBundleShippingService {

    private final ShippingRepository repository;

    private final ShippingTranslator translator;

    @Transactional
    public void createOrBundle(final CreateOrBundleShippingCommand command) {
        final List<ValidationError> validationErrors = validateErrors(command);

        if (!validationErrors.isEmpty()) {
            throw new ValidationErrorException(validationErrors);
        }

        final Shipping newShipping = translator.translate(command);

        repository.findById(new ShippingNumber(command.shippingNumber()))
                .ifPresentOrElse(
                        shipping -> shipping.bundle(newShipping),
                        () -> repository.save(newShipping)
                );
    }



    private List<ValidationError> validateErrors(final CreateOrBundleShippingCommand command) {
        final List<ValidationError> errors = new ArrayList<>();

        if (Strings.isBlank(command.shippingNumber())) {
            errors.add(ValidationError.of("shippingNumber", "required.shippingNumber"));
        }

        if (Strings.isBlank(command.chargeType())) {
            errors.add(ValidationError.of("chargeType", "required.chargeType"));
        } else {
            try {
                ChargeType.valueOf(command.chargeType());
            } catch (IllegalArgumentException e) {
                errors.add(ValidationError.of("chargeType", "invalid.chargeType"));
            }
        }

        if (Strings.isBlank(command.recipientName())) {
            errors.add(ValidationError.of("recipientName", "required.recipientName"));
        }

        if (Strings.isBlank(command.recipientTel1())) {
            errors.add(ValidationError.of("recipientTel1", "required.recipientTel1"));
        } else if (Validator.isInvalidPhoneNumber(command.recipientTel1())) {
            errors.add(ValidationError.of("recipientTel1", "invalid.recipientTel1"));
        }

        if (Strings.isNotBlank(command.recipientTel2())
            && Validator.isInvalidPhoneNumber(command.recipientTel2())) {
            errors.add(ValidationError.of("recipientTel2", "invalid.recipientTel2"));
        }

        if (Strings.isNotBlank(command.address1())) {
            errors.add(ValidationError.of("address1", "required.address1"));
        }

        if (Strings.isEmpty(command.zipCode())) {
            errors.add(ValidationError.of("zipCode", "required.zipCode"));
        }

        return errors;
    }

}
