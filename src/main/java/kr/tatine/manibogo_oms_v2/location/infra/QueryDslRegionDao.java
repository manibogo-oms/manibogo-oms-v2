package kr.tatine.manibogo_oms_v2.location.infra;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.location.domain.region.Region;
import kr.tatine.manibogo_oms_v2.location.domain.region.RegionQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.location.domain.region.QRegion.region;


@Repository
@RequiredArgsConstructor
public class QueryDslRegionDao implements RegionQueryUseCase {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Region> findAll(int level, String parentCode) {
        return queryFactory.selectFrom(region)
                .where(region.level.eq(level), eqParentCode(parentCode))
                .fetch();
    }

    private BooleanExpression eqParentCode(String parentCode) {
        if (Strings.isEmpty(parentCode)) return null;
        return region.parentCode.code.eq(parentCode);
    }

}
