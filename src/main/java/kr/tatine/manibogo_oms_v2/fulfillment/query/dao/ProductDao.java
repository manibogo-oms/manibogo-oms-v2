package kr.tatine.manibogo_oms_v2.fulfillment.query.dao;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.Product;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface ProductDao extends Repository<Product, ProductNumber> {

    @Query("""
        SELECT new kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto(p.number.productNumber, p.name, p.priority.priority, p.isEnabled)
        FROM Product p
        ORDER BY p.priority.priority
    """)
    Page<ProductDto> findAll(Pageable pageable);

    @Query("""
        SELECT new kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto(p.number.productNumber, p.name, p.priority.priority, p.isEnabled)
        FROM Product p
        WHERE p.isEnabled = true
        ORDER BY p.priority.priority
    """)
    List<ProductDto> findEnabled();

}
