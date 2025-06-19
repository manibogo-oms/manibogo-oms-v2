package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product changeName(Product product, String newName) {
        if (Objects.equals(product.getName(),newName)) {
            return product;
        }

        if (productRepository.countByName(newName) > 0) {
            throw new ProductNameDuplicatedException();
        }

        product.changeName(newName);
        return product;
    }

}
