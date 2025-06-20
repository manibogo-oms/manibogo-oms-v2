package kr.tatine.manibogo_oms_v2.product.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.product.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditProductService {

    private final ProductService productService;

    private final ProductRepository productRepository;

    @Transactional
    public void editProduct(EditProductCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (command.productNumber() == null || command.productNumber().isBlank()) {
            errors.add(ValidationError.of("number", "required.product.number"));
        }

        if (command.productName() == null || command.productName().isBlank()) {
            errors.add(ValidationError.of("name", "required.product.name"));
        }

        if (command.priority() == null) {
            errors.add(ValidationError.of("priority", "required.product.priority"));
        } else if (command.priority() < 0 || command.priority() > 9999) {
            errors.add(ValidationError.of("priority", "range.product.priority"));
        }

        if (command.isEnabled() == null) {
            errors.add(ValidationError.of("isEnabled", "required.product.isEnabled"));
        }

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }


        final ProductNumber productNumber = new ProductNumber(command.productNumber());

        final Product product = productRepository.findById(productNumber)
                .orElseThrow(ProductNotFoundException::new);

        productService.changeName(product, command.productName());
        product.changePriority(new Priority(command.priority()));
        product.changeIsEnabled(command.isEnabled());
    }


}
