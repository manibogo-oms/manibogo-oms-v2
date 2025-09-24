package kr.tatine.manibogo_oms_v2.region.infra;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.tatine.manibogo_oms_v2.region.query.dao.RegionDao;
import kr.tatine.manibogo_oms_v2.region.query.dto.RegionDto;
import kr.tatine.manibogo_oms_v2.region.query.dto.RegionView;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.region.query.dto.QRegionView.regionView;

@Repository
@RequiredArgsConstructor
public class QueryDslRegionDao implements RegionDao {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    private static final String BY_LEVEL_QUERY =
            "SELECT rv FROM RegionView rv WHERE rv.level = :level AND rv.parentCode = :parentCode";


    @Override
    public List<RegionDto> findDistinctAll() {

        final String query = """
            SELECT DISTINCT new kr.tatine.manibogo_oms_v2.region.query.dto.RegionDto(r.sido, r.sigungu) FROM ZipCodeRegion r
        """;
        return em.createQuery(query, RegionDto.class).getResultList();
    }

    @Override
    public List<RegionView> findAll(int level, String parentCode) {
        return queryFactory.selectFrom(regionView)
                .where(regionView.level.eq(level), eqParentCode(parentCode))
                .fetch();
    }

    private BooleanExpression eqParentCode(String parentCode) {
        if (Strings.isEmpty(parentCode)) {
            return null;
        }
        return regionView.parentCode.code.eq(parentCode);
    }

}
