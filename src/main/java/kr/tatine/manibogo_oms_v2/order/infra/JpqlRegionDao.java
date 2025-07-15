package kr.tatine.manibogo_oms_v2.order.infra;

import jakarta.persistence.EntityManager;
import kr.tatine.manibogo_oms_v2.order.query.dao.RegionDao;
import kr.tatine.manibogo_oms_v2.order.query.dto.RegionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpqlRegionDao implements RegionDao {

    private final EntityManager em;

    @Override
    public List<RegionDto> findDistinctAll() {

        final String query = """
            SELECT DISTINCT new kr.tatine.manibogo_oms_v2.order.query.dto.RegionDto(r.sido, r.sigungu) FROM Region r
        """;

        return em.createQuery(query, RegionDto.class).getResultList();

    }
}
