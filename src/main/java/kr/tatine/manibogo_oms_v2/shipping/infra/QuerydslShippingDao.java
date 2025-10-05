package kr.tatine.manibogo_oms_v2.shipping.infra;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.ShippingQuery;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.port.in.ShippingQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.shipping.command.domain.QShipping.shipping;
import static kr.tatine.manibogo_oms_v2.shipping.query.entity.QShippingJuso.shippingJuso;
import static kr.tatine.manibogo_oms_v2.shipping.query.entity.QShippingOrderAgg.shippingOrderAgg;

@Repository
@RequiredArgsConstructor
public class QuerydslShippingDao implements ShippingQueryUseCase {

    private final JPAQueryFactory queryFactory;

    private static final String SHIPPING_METHOD_EXPR = """
        CASE WHEN type(shipping) = 'DIRECT'
             THEN kr.tatine.manibogo_oms_v2.common.model.ShippingMethod.DIRECT
             ELSE kr.tatine.manibogo_oms_v2.common.model.ShippingMethod.PARCEL
         END
    """;

    @Override
    public Page<ShippingView> findAll(ShippingQuery filter, Pageable pageable) {

        final Predicate[] predicates = parseFilter(filter);

        final List<ShippingView> content = queryFactory.select(serialize())
                .from(shipping)
                .join(shippingJuso).on(shippingJuso.shippingNumber.eq(shipping.number))
                .join(shippingOrderAgg).on(shippingOrderAgg.shippingNumber.eq(shipping.number))
                .where(predicates)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final JPAQuery<Long> countQuery = queryFactory.select(shipping.count()).from(shipping).where(predicates);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchFirst);
    }

    private static ConstructorExpression<ShippingView> serialize() {
        return Projections.constructor(
                ShippingView.class,
                shipping.number.as("shippingNumber"),
                Expressions.template(ShippingMethod.class, SHIPPING_METHOD_EXPR).as("shippingMethod"),
                shipping.state.as("shippingState"),
                shippingOrderAgg.primaryOrderNumber,
                shippingOrderAgg.primaryOrderState,
                shippingOrderAgg.primaryOrderProduct,
                shippingOrderAgg.primaryOrderQuantity,
                shippingOrderAgg.totalOrderCount,
                shippingOrderAgg.totalOrderQuantity,
                shippingJuso.sido,
                shippingJuso.sigungu,
                shipping.recipient.address.address1.as("address1"),
                shipping.recipient.address.address2.as("address2"),
                shipping.recipient.address.zipCode.as("zipCode"),
                shipping.recipient.name.as("recipientName"),
                shipping.recipient.phoneNumber1.phoneNumber.as("recipientTel1"),
                shipping.recipient.phoneNumber2.phoneNumber.as("recipientTel2")
        );
    }

    private static Predicate[] parseFilter(ShippingQuery filter) {
        if (filter == null) return new Predicate[0];

        return new Predicate[]{
                eqState(filter),
                parseSidoAndSigungu(filter),
                parseDetailSearch(filter)
        };
    }

    private static BooleanExpression eqState(ShippingQuery filter) {
        if (filter.state() == null) return null;
        return shipping.state.eq(filter.state());
    }

    private static BooleanExpression parseSidoAndSigungu(ShippingQuery filter) {
        if (Strings.isBlank(filter.sido())) return null;
        if (Strings.isBlank(filter.sigungu())) return shippingJuso.admCode.startsWith(filter.sido().substring(0, 2));

        return shippingJuso.admCode.startsWith(filter.sigungu().substring(0, 5));
    }

    private static BooleanExpression parseDetailSearch(ShippingQuery filter) {
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
