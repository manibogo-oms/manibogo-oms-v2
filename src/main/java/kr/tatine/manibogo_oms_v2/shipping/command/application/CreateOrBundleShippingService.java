package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateOrBundleShippingService {

    private final ShippingRepository shippingRepository;

    private final ShippingTranslator translator;

    @Transactional
    public void createOrBundle(final CreateOrBundleShippingCommand command) {
        final List<ValidationError> validationErrors = validateErrors(command);

        if (!validationErrors.isEmpty()) {
            throw new ValidationErrorException(validationErrors);
        }

        final Shipping newShipping = translator.translate(command);

        shippingRepository.findByShippingOrderNumber(command.orderNumber())
                        .ifPresent(shipping -> shipping.removeOrder(command.orderNumber()));

        shippingRepository.findById(command.shippingNumber()).ifPresentOrElse(
                shipping -> shipping.bundle(newShipping),
                () -> shippingRepository.save(newShipping));
    }



    private List<ValidationError> validateErrors(final CreateOrBundleShippingCommand command) {
        final List<ValidationError> errors = new ArrayList<>();

        if (Objects.isNull(command.shippingNumber())) {
            errors.add(ValidationError.of("shippingNumber", "required.shippingNumber"));
        }

        if (Objects.isNull(command.shippingMethod())) {
            errors.add(ValidationError.of("chargeType", "required.chargeType"));
        }

        if (Objects.isNull(command.recipient())) {
            errors.add(ValidationError.of("recipientName", "required.recipient"));
        }

        return errors;
    }

}
