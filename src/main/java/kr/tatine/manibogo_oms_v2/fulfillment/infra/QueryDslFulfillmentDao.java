package kr.tatine.manibogo_oms_v2.fulfillment.infra;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.FulfillmentDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.fulfillment.query.dto.QFulfillmentDto.fulfillmentDto;

@Repository
@RequiredArgsConstructor
public class QueryDslFulfillmentDao implements FulfillmentDao {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FulfillmentDto> findAll(Pageable pageable) {

        final List<FulfillmentDto> content = queryFactory
                .selectFrom(fulfillmentDto)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(fulfillmentDto.itemOrderNumber.desc())
                .fetch();

        final JPAQuery<Long> countQuery = queryFactory
                .select(fulfillmentDto.count())
                .from(fulfillmentDto);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
