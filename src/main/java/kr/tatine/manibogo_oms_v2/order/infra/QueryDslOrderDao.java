package kr.tatine.manibogo_oms_v2.order.infra;

import com.querydsl.core.types.*;
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

import static kr.tatine.manibogo_oms_v2.order.command.domain.model.QOrder.order;
import static kr.tatine.manibogo_oms_v2.order.query.dto.QOrderStateHistory.orderStateHistory;
import static kr.tatine.manibogo_oms_v2.product.command.domain.QProduct.product;
import static kr.tatine.manibogo_oms_v2.region.command.domain.QZipCodeRegion.zipCodeRegion;

@Repository
@RequiredArgsConstructor
public class QueryDslOrderDao implements OrderDao {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderDto> findAll(Pageable pageable, OrderQueryParams queryParams) {

        final Predicate[] predicates = getPredicates(queryParams);

        final List<OrderDto> content = getQuery()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(pageable.getSort()))
                .where(predicates)
                .fetch();

        final JPAQuery<Long> countQuery = queryFactory
                .select(order.count())
                .from(order)
                .leftJoin(product).on(order.product.productNumber.eq(product.number))
                .leftJoin(orderStateHistory).on(order.number.orderNumber.eq(orderStateHistory.orderNumber))
                .leftJoin(zipCodeRegion).on(order.shippingInfo.address.zipCode.eq(zipCodeRegion.zipCode))
                .where(predicates);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public List<OrderDto> findAll(OrderQueryParams queryParams) {
        return getQuery()
                .where(getPredicates(queryParams))
                .fetch();
    }

    @Override
    public Optional<OrderDto> findById(String orderNumber) {
        return Optional.ofNullable(getQuery()
                .where(order.number.orderNumber.eq(orderNumber))
                .fetchOne()
        );
    }

    @Override
    public List<OrderDto> findByShippingBundleNumber(String shippingBundleNumber) {
        return getQuery()
                .where(order.shippingBundleNumber.shippingBundleNumber.eq(shippingBundleNumber))
                .fetch();
    }

    private JPAQuery<OrderDto> getQuery() {
        return queryFactory
                .select(Projections.fields(
                        OrderDto.class,
                        order.number.orderNumber,
                        order.salesChannel,
                        order.state.as("orderState"),
                        product.name.as("productName"),
                        order.product.option1.key.as("optionKey1"),
                        order.product.option1.value.as("optionValue1"),
                        order.product.option2.key.as("optionKey2"),
                        order.product.option2.value.as("optionValue2"),
                        order.product.option3.key.as("optionKey3"),
                        order.product.option3.value.as("optionValue3"),
                        order.product.amount,
                        zipCodeRegion.sido,
                        zipCodeRegion.sigungu,
                        order.customer.name.as("customerName"),
                        order.customer.phoneNumber.phoneNumber.as("customerTel"),
                        order.shippingInfo.recipientName.as("recipientName"),
                        order.shippingInfo.recipientTel1.phoneNumber.as("recipientTel1"),
                        order.shippingInfo.recipientTel2.phoneNumber.as("recipientTel2"),
                        order.shippingInfo.address.address1.as("recipientAddr1"),
                        order.shippingInfo.address.address2.as("recipientAddr2"),
                        order.shippingInfo.address.zipCode.as("recipientZipCode"),
                        orderStateHistory.placedAt,
                        order.dispatchDeadline,
                        order.preferredShippingDate,
                        orderStateHistory.purchasedAt,
                        orderStateHistory.dispatchedAt,
                        orderStateHistory.shippedAt,
                        order.trackingInfo.trackingNumber,
                        orderStateHistory.confirmedAt,
                        orderStateHistory.cancelledAt,
                        orderStateHistory.refundedAt,
                        order.memo.purchaseMemo,
                        order.memo.shippingMemo,
                        order.memo.adminMemo,
                        order.customer.message.as("customerMessage"),
                        order.shippingInfo.method.as("shippingMethod"),
                        order.shippingInfo.chargeType.as("shippingChargeType"),
                        order.trackingInfo.companyName.as("shippingCompanyName"),
                        order.product.price.value.as("finalPrice")
                ))
                .from(order)
                .leftJoin(product).on(order.product.productNumber.eq(product.number))
                .leftJoin(orderStateHistory).on(order.number.orderNumber.eq(orderStateHistory.orderNumber))
                .leftJoin(zipCodeRegion).on(order.shippingInfo.address.zipCode.eq(zipCodeRegion.zipCode));
    }

    private Predicate[] getPredicates(OrderQueryParams queryParams) {

        return new Predicate[]{
                product.isEnabled.isTrue(),
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
            return zipCodeRegion.sido.eq(sido);
        }

        return zipCodeRegion.sido.eq(sido).and(zipCodeRegion.sigungu.eq(sigungu));
    }

    private BooleanExpression eqDetailSearch(OrderQueryParams queryParams) {
        if (queryParams.getDetailSearchParam() == null
                || queryParams.getDetailSearch() == null
                || queryParams.getDetailSearch().isBlank()) {
            return null;
        }

        return switch (queryParams.getDetailSearchParam()) {
            case ORDER_NUMBER -> order.number.orderNumber.eq(queryParams.getDetailSearch());
            case CUSTOMER_NAME -> order.customer.name.eq(queryParams.getDetailSearch());
            case CUSTOMER_TEL -> order.customer.phoneNumber.phoneNumber.eq(queryParams.getDetailSearch());
            case RECIPIENT_NAME -> order.shippingInfo.recipientName.eq(queryParams.getDetailSearch());
            case RECIPIENT_TEL_1 -> order.shippingInfo.recipientTel1.phoneNumber.eq(queryParams.getDetailSearch());
            case RECIPIENT_TEL_2 -> order.shippingInfo.recipientTel2.phoneNumber.eq(queryParams.getDetailSearch());
            case RECIPIENT_ADDRESS -> order.shippingInfo.address.address1.contains(queryParams.getDetailSearch());
            case SHIPPING_TRACKING_NUMBER -> order.trackingInfo.trackingNumber.eq(queryParams.getDetailSearch());
            case SHIPPING_BUNDLE_NUMBER -> order.shippingBundleNumber.shippingBundleNumber.eq(queryParams.getDetailSearch());
        };
    }

    private BooleanExpression eqDateSearch(OrderQueryParams queryParams) {
        if (queryParams.getDateSearchParam() == null
                || queryParams.getStartedAt() == null
                || queryParams.getEndedAt() == null) {
            return null;
        }

        return switch (queryParams.getDateSearchParam()) {
            case PLACED_AT -> orderStateHistory.placedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case DISPATCH_DEADLINE -> order.dispatchDeadline.between(queryParams.getStartDate(), queryParams.getEndDate());
            case PREFERRED_SHIPS_ON -> order.preferredShippingDate.between(queryParams.getStartDate(), queryParams.getEndDate());
            case PURCHASED_AT -> orderStateHistory.purchasedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case DISPATCHED_AT -> orderStateHistory.dispatchedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case SHIPPED_AT -> orderStateHistory.shippedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case CONFIRMED_AT -> orderStateHistory.confirmedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case CANCELLED_AT -> orderStateHistory.cancelledAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
            case REFUNDED_AT -> orderStateHistory.refundedAt.between(queryParams.getStartedAt(), queryParams.getEndedAt());
        };
    }

    private BooleanExpression eqItemOrderStatus(OrderQueryParams queryParams) {
        if (queryParams.getItemOrderState() == null) return null;

        return order.state.eq(queryParams.getItemOrderState());
    }

    private BooleanExpression eqSalesChannel(OrderQueryParams queryParams) {
        if (queryParams.getSalesChannel() == null) return null;

        return order.salesChannel.eq(queryParams.getSalesChannel());
    }

    private BooleanExpression eqProductNumber(OrderQueryParams queryParams) {
        if (queryParams.getProductNumber() == null || queryParams.getProductNumber().isBlank()) return null;

        return order.product.productNumber.productNumber.eq(queryParams.getProductNumber());
    }

    private OrderSpecifier<?>[] getOrderSpecifiers(final Sort sort) {

        final ArrayList<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        final OrderSpecifier<LocalDateTime> defaultOrderSpecifier = orderStateHistory.placedAt.desc();

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
                case PLACED_AT -> orderStateHistory.placedAt;
                case DISPATCH_DEADLINE -> order.dispatchDeadline;
                case PREFERRED_SHIPS_ON -> order.preferredShippingDate;
                case PURCHASED_AT -> orderStateHistory.purchasedAt;
                case DISPATCHED_AT -> orderStateHistory.dispatchedAt;
                case SHIPPED_AT -> orderStateHistory.shippedAt;
                case CANCELLED_AT -> orderStateHistory.confirmedAt;
                case CONFIRMED_AT -> orderStateHistory.cancelledAt;
                case REFUNDED_AT -> orderStateHistory.refundedAt;
            };

        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

}
