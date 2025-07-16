package kr.tatine.manibogo_oms_v2.region.infra;

import jakarta.persistence.EntityManager;
import kr.tatine.manibogo_oms_v2.region.query.dao.RegionDao;
import kr.tatine.manibogo_oms_v2.region.query.dto.RegionDto;
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
            SELECT DISTINCT new kr.tatine.manibogo_oms_v2.region.query.dto.RegionDto(r.sido, r.sigungu) FROM ZipCodeRegion r
        """;
        return em.createQuery(query, RegionDto.class).getResultList();
    }
}
