package kr.tatine.manibogo_oms_v2.region.infra;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.region.query.port.in.RegionQueryUseCase;
import kr.tatine.manibogo_oms_v2.region.query.dto.out.RegionView;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.region.query.dto.out.QRegionView.*;

@Repository
@RequiredArgsConstructor
public class QueryDslRegionDao implements RegionQueryUseCase {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RegionView> findAll(int level, String parentCode) {
        return queryFactory.selectFrom(regionView)
                .where(regionView.level.eq(level), eqParentCode(parentCode))
                .fetch();
    }

    private BooleanExpression eqParentCode(String parentCode) {
        if (Strings.isEmpty(parentCode)) return null;
        return regionView.parentCode.code.eq(parentCode);
    }

}
