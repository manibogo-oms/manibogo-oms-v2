package kr.tatine.manibogo_oms_v2.shipping.infra;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.CourierShipping;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.DirectShipping;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.QDirectShipping;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.ShippingFilter;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingViewDao;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.region.command.domain.QZipCodeRegion.zipCodeRegion;
import static kr.tatine.manibogo_oms_v2.shipping.command.domain.QShipping.shipping;

@Repository
@RequiredArgsConstructor
public class QuerydslShippingViewDao implements ShippingViewDao {

    private final JPAQueryFactory queryFactory;

    private static final String SHIPPING_METHOD_EXPR = """
        CASE WHEN type(shipping) = 'DIRECT'
             THEN kr.tatine.manibogo_oms_v2.common.model.ShippingMethod.DIRECT
             ELSE kr.tatine.manibogo_oms_v2.common.model.ShippingMethod.PARCEL
         END
    """;

    @Override
    public Page<ShippingView> findAll(ShippingFilter filter, Pageable pageable) {

        final Predicate[] predicates = parseFilter(filter);

        final List<ShippingView> content = queryFactory.select(
                Projections.constructor(
                    ShippingView.class,
                    shipping.number.as("shippingNumber"),
                    Expressions.template(ShippingMethod.class, SHIPPING_METHOD_EXPR),
                    shipping.state.as("shippingState"),
                    shipping.recipient.address.address1.as("address1"),
                    shipping.recipient.address.address2.as("address2"),
                    shipping.recipient.address.zipCode.as("zipCode"),
                    zipCodeRegion.sido.as("sido"),
                    zipCodeRegion.sigungu.as("sigungu"),
                    shipping.recipient.name.as("recipientName"),
                    shipping.recipient.phoneNumber1.phoneNumber.as("recipientTel1"),
                    shipping.recipient.phoneNumber2.phoneNumber.as("recipientTel2")
                ))
                .from(shipping)
                .where(predicates)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .leftJoin(zipCodeRegion).on(zipCodeRegion.zipCode.eq(shipping.recipient.address.zipCode))
                .fetch();

        final JPAQuery<Long> countQuery = queryFactory.select(shipping.count()).from(shipping).where(predicates);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchFirst);
    }

    private static Predicate[] parseFilter(ShippingFilter filter) {
        if (filter == null) return new Predicate[0];

        return new Predicate[]{
                eqState(filter),
                parseSidoAndSigungu(filter),
                parseDetailSearch(filter)
        };
    }

    private static BooleanExpression eqState(ShippingFilter filter) {
        if (filter.state() == null) return null;
        return shipping.state.eq(filter.state());
    }

    private static BooleanExpression parseSidoAndSigungu(ShippingFilter filter) {
        if (Strings.isBlank(filter.sido())) return null;

        final BooleanExpression query = zipCodeRegion.sido.eq(filter.sido());
        if (Strings.isBlank(filter.sigungu())) return query;

        return query.and(zipCodeRegion.sigungu.eq(filter.sigungu()));
    }

    private static BooleanExpression parseDetailSearch(ShippingFilter filter) {
        if (filter.detailSearchType() == null || Strings.isBlank(filter.keyword())) {
            return null;
        }

        return switch (filter.detailSearchType()) {
            case SHIPPING_NUMBER -> shipping.number.shippingNumber.eq(filter.keyword());
            case RECIPIENT_NAME -> shipping.recipient.name.eq(filter.keyword());
            case RECIPIENT_TEL1 -> shipping.recipient.phoneNumber1.phoneNumber.eq(filter.keyword());
            case RECIPIENT_TEL2 -> shipping.recipient.phoneNumber2.phoneNumber.eq(filter.keyword());
            case ADDRESS1 -> shipping.recipient.address.address1.eq(filter.keyword());
            case ADDRESS2 -> shipping.recipient.address.address2.eq(filter.keyword());
        };
    }
}
