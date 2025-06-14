package kr.tatine.manibogo_oms_v2.fulfillment.infra;

import jakarta.persistence.EntityManager;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.ProductDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpqlProductDao implements ProductDao {

    private final EntityManager em;


    @Override
    public List<ProductDto> findAll() {
        return em.createQuery(
    "SELECT new kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto(p.number.productNumber, p.name) FROM Product p ORDER BY p.priority.priority ASC", ProductDto.class)
                .getResultList();
    }


}
