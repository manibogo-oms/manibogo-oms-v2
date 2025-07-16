package kr.tatine.manibogo_oms_v2.infra;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.logistics.query.dao.LogisticsDao;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsDto;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsQueryParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.logistics.query.dto.QLogisticsDto.logisticsDto;

@Repository
@RequiredArgsConstructor
public class QuerydslLogisticsDao implements LogisticsDao {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<LogisticsDto> findAll(Pageable pageable, LogisticsQueryParams queryParams) {

        final Predicate[] predicates = new Predicate[] {};

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
}
