package kr.tatine.manibogo_oms_v2.variant.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.common.model.Option;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import kr.tatine.manibogo_oms_v2.variant.command.domain.Variant;
import kr.tatine.manibogo_oms_v2.variant.command.domain.VariantId;
import kr.tatine.manibogo_oms_v2.variant.command.domain.VariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddVariantService {

    private final VariantRepository variantRepository;

    @Transactional
    public void add(VariantCommand command) {

        final List<ValidationError> errors = VariantCommandValidator.validate(command);

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final VariantId variantId = getVariantId(command);

        if (variantRepository.existsById(variantId)) {
            throw new VariantDuplicatedException();
        }

        variantRepository.save(new Variant(variantId, command.label()));
    }

    private VariantId getVariantId(VariantCommand command) {
        final ProductNumber productNumber = new ProductNumber(command.productNumber());
        final Option option = new Option(command.key(), command.value());

        return new VariantId(option, productNumber);
    }

}
