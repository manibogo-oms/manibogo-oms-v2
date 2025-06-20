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
public class EditVariantService {

    private final VariantRepository variantRepository;

    @Transactional
    public void edit(VariantCommand command) {

        final List<ValidationError> errors =
                VariantCommandValidator.validate(command);

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final ProductNumber productNumber = new ProductNumber(command.productNumber());
        final Option option = new Option(command.key(), command.value());

        final VariantId variantId = new VariantId(option, productNumber);

        final Variant variant = variantRepository
                .findById(variantId)
                .orElseThrow(VariantNotFoundException::new);

        variant.changeLabel(command.label());
    }

}
