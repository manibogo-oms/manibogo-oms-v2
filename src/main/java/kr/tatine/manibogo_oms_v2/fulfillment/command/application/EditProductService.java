package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.Valid;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.Priority;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.Product;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class EditProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void editProduct(@Valid EditProductCommand command) {

        final ProductNumber productNumber = new ProductNumber(command.productNumber());

        final Product product = productRepository.findById(productNumber)
                .orElseThrow(ProductNotFoundException::new);

        product.changeName(productRepository, command.productName());
        product.changePriority(new Priority(command.priority()));
        product.changeIsEnabled(command.isEnabled());
    }


}
