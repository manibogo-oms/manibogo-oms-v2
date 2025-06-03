package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, ProductNumber> {

    @Query("SELECT p.priority FROM Product p ORDER BY p.priority.priority DESC LIMIT 1")
    Optional<Priority> findHighestPriority();

}
