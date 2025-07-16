package kr.tatine.manibogo_oms_v2.product.query;

import kr.tatine.manibogo_oms_v2.product.command.domain.Product;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductDao extends Repository<Product, ProductNumber> {

    @Query("""
        SELECT new kr.tatine.manibogo_oms_v2.product.query.ProductDto(
            p.number.productNumber,
            p.name,
            p.priority.priority,
            p.isEnabled
        )
        FROM Product p
        ORDER BY p.priority.priority
    """)
    List<ProductDto> findAll();

    @Query("""
        SELECT new kr.tatine.manibogo_oms_v2.product.query.ProductDto(p.number.productNumber, p.name, p.priority.priority, p.isEnabled)
        FROM Product p
        ORDER BY p.priority.priority
    """)
    Page<ProductDto> findAll(Pageable pageable);

    @Query("""
        SELECT new kr.tatine.manibogo_oms_v2.product.query.ProductDto(p.number.productNumber, p.name, p.priority.priority, p.isEnabled)
        FROM Product p
        WHERE p.isEnabled = true
        ORDER BY p.priority.priority
    """)
    List<ProductDto> findEnabled();

    @Query("SELECT new kr.tatine.manibogo_oms_v2.product.query.ProductDto(" +
            "p.number.productNumber, " +
            "p.name, " +
            "p.priority.priority, " +
            "p.isEnabled) " +
            "FROM Product p " +
            "WHERE p.number.productNumber = :productNumber")
    Optional<ProductDto> findByNumber(String productNumber);

}
