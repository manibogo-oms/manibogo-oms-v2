package kr.tatine.manibogo_oms_v2.order.infra;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.order.query.dao.OrderDao;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderDto;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderQueryParams;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderSortParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static kr.tatine.manibogo_oms_v2.order.query.dto.QOrderDto.orderDto;

@Repository
@RequiredArgsConstructor
public class QueryDslOrderDao implements OrderDao {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderDto> findAll(Pageable pageable, OrderQueryParams queryParams) {

        final Predicate[] predicates = getPredicates(queryParams);

        final List<OrderDto> content = queryFactory
                .selectFrom(orderDto)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(pageable.getSort()))
                .where(predicates)
                .fetch();

        final JPAQuery<Long> countQuery = queryFactory
                .select(orderDto.count())
                .from(orderDto)
                .where(predicates);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public List<OrderDto> findAll(OrderQueryParams queryParams) {
        return queryFactory
                .selectFrom(orderDto)
                .where(getPredicates(queryParams))
                .fetch();
    }

    @Override
    public Optional<OrderDto> findById(String orderNumber) {
        return Optional.ofNullable(
                queryFactory.selectFrom(orderDto)
                .where(orderDto.orderNumber.eq(orderNumber))
                .fetchOne()
        );
    }

    @Override
    public List<OrderDto> findByShippingBundleNumber(String shippingBundleNumber) {
        return queryFactory.selectFrom(orderDto)
                .where(orderDto.shippingBundleNumber.eq(shippingBundleNumber))
                .fetch();
    }

    private Predicate[] getPredicates(OrderQueryParams queryParams) {

        return new Predicate[]{
                eqItemOrderStatus(queryParams),
                eqSalesChannel(queryParams),
                eqProductNumber(queryParams),
                eqDetailSearch(queryParams),
                eqDateSearch(queryParams),
                eqSidoAndSigungu(queryParams)
        };
    }

    private BooleanExpression eqSidoAndSigungu(OrderQueryParams queryParams) {

        final String sido = queryParams.getSido();
        final String sigungu = queryParams.getSigungu();

        if (sido == null || sido.isBlank()) return null;

        if (sigungu == null || sigungu.isBlank()) {
            return orderDto.sido.eq(sido);
        }

        return orderDto.sido.eq(sido).and(orderDto.sigungu.eq(sigungu));
    }

    private BooleanExpression eqDetailSearch(OrderQueryParams queryParams) {
        if (queryParams.getDetailSearchParam() == null
                || queryParams.getDetailSearch() == null
                || queryParams.getDetailSearch().isBlank()) {
            return null;
        }

        return switch (queryParams.getDetailSearchParam()) {
            case ITEM_ORDER_NUMBER -> orderDto.orderNumber.eq(queryParams.getDetailSearch());
            case ORDER_NUMBER -> orderDto.orderNumber.eq(queryParams.getDetailSearch());
            case CUSTOMER_NAME -> orderDto.customerName.eq(queryParams.getDetailSearch());
            case CUSTOMER_TEL -> orderDto.customerPhoneNumber.eq(queryParams.getDetailSearch());
            case RECIPIENT_NAME -> orderDto.recipientName.eq(queryParams.getDetailSearch());
            case RECIPIENT_TEL_1 -> orderDto.recipientPhoneNumber1.eq(queryParams.getDetailSearch());
            case RECIPIENT_TEL_2 -> orderDto.recipientPhoneNumber2.eq(queryParams.getDetailSearch());
            case RECIPIENT_ADDRESS -> orderDto.recipientAddress.contains(queryParams.getDetailSearch());
            case SHIPPING_TRACKING_NUMBER -> orderDto.shippingTrackingNumber.eq(queryParams.getDetailSearch());
            case SHIPPING_BUNDLE_NUMBER -> orderDto.shippingBundleNumber.eq(queryParams.getDetailSearch());
        };
    }

    private BooleanExpression eqDateSearch(OrderQueryParams queryParams) {
        if (queryParams.getDateSearchParam() == null
                || queryParams.getStartedAt() == null
                || queryParams.getEndedAt() == null) {
            return null;
        }

        return switch (queryParams.getDateSearchParam()) {
            case PLACED_AT -> orderDto.placedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case DISPATCH_DEADLINE -> orderDto.dispatchDeadline.between(queryParams.getStartDate(), queryParams.getEndDate());
            case PREFERRED_SHIPS_ON -> orderDto.preferredShippingDate.between(queryParams.getStartDate(), queryParams.getEndDate());
            case PURCHASED_AT -> orderDto.purchasedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case DISPATCHED_AT -> orderDto.dispatchedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case SHIPPED_AT -> orderDto.shippedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case CONFIRMED_AT -> orderDto.confirmedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case CANCELLED_AT -> orderDto.cancelledAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case REFUNDED_AT -> orderDto.refundedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
        };
    }

    private BooleanExpression eqItemOrderStatus(OrderQueryParams queryParams) {
        if (queryParams.getItemOrderState() == null) return null;

        return orderDto.orderState.eq(queryParams.getItemOrderState());
    }

    private BooleanExpression eqSalesChannel(OrderQueryParams queryParams) {
        if (queryParams.getSalesChannel() == null) return null;

        return orderDto.salesChannel.eq(queryParams.getSalesChannel());
    }

    private BooleanExpression eqProductNumber(OrderQueryParams queryParams) {
        if (queryParams.getProductNumber() == null || queryParams.getProductNumber().isBlank()) return null;

        return orderDto.productNumber.eq(queryParams.getProductNumber());
    }

    private OrderSpecifier<?>[] getOrderSpecifiers(final Sort sort) {

        final ArrayList<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        final OrderSpecifier<LocalDateTime> defaultOrderSpecifier = orderDto.placedAt.desc();

        for (final Sort.Order order : sort) {
            final Order direction = order.isAscending()
                    ? Order.ASC
                    : Order.DESC;

            final Path<?> propertyPath = getPropertyPath(order.getProperty());

            if (propertyPath == null) continue;

            orderSpecifiers.add(new OrderSpecifier(direction, propertyPath));
        }

        orderSpecifiers.add(defaultOrderSpecifier);

        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    private Path<?> getPropertyPath(String property) {
        try {
            return switch (OrderSortParam.valueOf(property)) {
                case PLACED_AT -> orderDto.placedAt;
                case DISPATCH_DEADLINE -> orderDto.dispatchDeadline;
                case PREFERRED_SHIPS_ON -> orderDto.preferredShippingDate;
                case PURCHASED_AT -> orderDto.purchasedAt;
                case DISPATCHED_AT -> orderDto.dispatchedAt;
                case SHIPPED_AT -> orderDto.shippedAt;
                case CANCELLED_AT -> orderDto.confirmedAt;
                case CONFIRMED_AT -> orderDto.cancelledAt;
                case REFUNDED_AT -> orderDto.refundedAt;
            };

        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

}
