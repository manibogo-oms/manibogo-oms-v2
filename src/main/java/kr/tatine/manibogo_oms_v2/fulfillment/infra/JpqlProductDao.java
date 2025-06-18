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

    private static final String FIND_ALL_QUERY = """
        SELECT new kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto(p.number.productNumber, p.name, p.priority.priority)\s
        FROM Product p\s
        ORDER BY p.priority.priority
    """;

    @Override
    public List<ProductDto> findAll() {
        return em.createQuery(FIND_ALL_QUERY, ProductDto.class).getResultList();

    }


}
