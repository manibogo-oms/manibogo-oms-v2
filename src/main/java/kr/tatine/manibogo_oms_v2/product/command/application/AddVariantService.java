package kr.tatine.manibogo_oms_v2.product.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.model.Option;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import kr.tatine.manibogo_oms_v2.product.command.domain.Variant;
import kr.tatine.manibogo_oms_v2.product.command.domain.VariantId;
import kr.tatine.manibogo_oms_v2.product.command.domain.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddVariantService {

    private final VariantRepository variantRepository;

    @Transactional
    public void add(AddVariantCommand command) {

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

        final ProductNumber productNumber = new ProductNumber(command.productNumber());
        final Option option = new Option(command.key(), command.value());

        final VariantId variantId = new VariantId(option, productNumber);

        if (variantRepository.existsById(variantId)) {
            errors.add(ValidationError.of("duplicate.variant"));
        }

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        variantRepository.save(new Variant(variantId, command.label()));
    }

}
