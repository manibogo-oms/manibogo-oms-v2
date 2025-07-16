package kr.tatine.manibogo_oms_v2.logistics.infra;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.logistics.query.dao.LogisticsDao;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsDto;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsQueryParams;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderQueryParams;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.logistics.query.dto.QLogisticsDto.logisticsDto;
import static kr.tatine.manibogo_oms_v2.order.query.dto.QOrderDto.orderDto;

@Repository
@RequiredArgsConstructor
public class QuerydslLogisticsDao implements LogisticsDao {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<LogisticsDto> findAll(Pageable pageable, LogisticsQueryParams queryParams) {

        final Predicate[] predicates = getPredicates(queryParams);

        final List<LogisticsDto> content = queryFactory
                .selectFrom(logisticsDto)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(predicates)
                .orderBy(logisticsDto.recentlyPlacedAt.desc())
                .fetch();

        final JPAQuery<Long> countQuery = queryFactory
                .select(logisticsDto.count())
                .from(logisticsDto)
                .where(predicates);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private Predicate[] getPredicates(LogisticsQueryParams queryParams) {

        return new Predicate[]{
                eqDetailSearch(queryParams),
                eqSidoAndSigungu(queryParams)
        };
    }

    private BooleanExpression eqSidoAndSigungu(LogisticsQueryParams queryParams) {

        final String sido = queryParams.getSido();
        final String sigungu = queryParams.getSigungu();

        if (Strings.isBlank(sido)) return null;

        if (Strings.isBlank(sigungu)) {
            return logisticsDto.sido.eq(sido);
        }

        return logisticsDto.sido.eq(sido).and(logisticsDto.sigungu.eq(sigungu));
    }

    private BooleanExpression eqDetailSearch(LogisticsQueryParams queryParams) {
        if (queryParams.getDetailSearchParam() == null
                || Strings.isBlank(queryParams.getDetailSearch())) {
            return null;
        }

        return switch (queryParams.getDetailSearchParam()) {
            case CUSTOMER_NAME -> logisticsDto.customerName.eq(queryParams.getDetailSearch());
            case CUSTOMER_TEL -> logisticsDto.customerTel.eq(queryParams.getDetailSearch());
            case RECIPIENT_NAME -> logisticsDto.recipientName.eq(queryParams.getDetailSearch());
            case RECIPIENT_TEL1 -> logisticsDto.recipientTel1.eq(queryParams.getDetailSearch());
            case RECIPIENT_TEL2 -> logisticsDto.recipientTel2.eq(queryParams.getDetailSearch());
            case ADDRESS -> logisticsDto.address1.contains(queryParams.getDetailSearch());
            case SHIPPING_BUNDLE_NUMBER -> logisticsDto.shippingBundleNumber.eq(queryParams.getDetailSearch());
        };
    }
}
