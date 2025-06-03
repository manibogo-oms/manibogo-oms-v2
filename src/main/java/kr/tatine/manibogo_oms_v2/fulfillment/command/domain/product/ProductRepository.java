package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, ProductNumber> {

//    Priority getLowestPriory();

}
