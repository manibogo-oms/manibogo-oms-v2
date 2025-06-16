package kr.tatine.manibogo_oms_v2.fulfillment.infra;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.FulfillmentDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentDto;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentQueryParams;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentSortParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static kr.tatine.manibogo_oms_v2.fulfillment.query.dto.QFulfillmentDto.fulfillmentDto;

@Repository
@RequiredArgsConstructor
public class QueryDslFulfillmentDao implements FulfillmentDao {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FulfillmentDto> findAll(Pageable pageable, FulfillmentQueryParams queryParams) {

        final Predicate[] predicates = getPredicates(queryParams);

        final List<FulfillmentDto> content = queryFactory
                .selectFrom(fulfillmentDto)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(pageable.getSort()))
                .where(predicates)
                .fetch();

        final JPAQuery<Long> countQuery = queryFactory
                .select(fulfillmentDto.count())
                .from(fulfillmentDto)
                .where(predicates);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public List<FulfillmentDto> findAll(FulfillmentQueryParams queryParams) {
        return queryFactory
                .selectFrom(fulfillmentDto)
                .where(getPredicates(queryParams))
                .fetch();
    }

    private Predicate[] getPredicates(FulfillmentQueryParams queryParams) {
        return new Predicate[]{
                eqItemOrderStatus(queryParams),
                eqSalesChannel(queryParams),
                eqProductNumber(queryParams),
                eqDetailSearch(queryParams),
                eqDateSearch(queryParams)
        };
    }

    private BooleanExpression eqDetailSearch(FulfillmentQueryParams queryParams) {
        if (queryParams.getDetailSearchParam() == null
                || queryParams.getDetailSearch() == null
                || queryParams.getDetailSearch().isBlank()) {
            return null;
        }

        return switch (queryParams.getDetailSearchParam()) {
            case ITEM_ORDER_NUMBER -> fulfillmentDto.itemOrderNumber.eq(queryParams.getDetailSearch());
            case ORDER_NUMBER -> fulfillmentDto.orderNumber.eq(queryParams.getDetailSearch());
            case CUSTOMER_NAME -> fulfillmentDto.customerName.eq(queryParams.getDetailSearch());
            case CUSTOMER_TEL -> fulfillmentDto.customerPhoneNumber.eq(queryParams.getDetailSearch());
            case RECIPIENT_NAME -> fulfillmentDto.recipientName.eq(queryParams.getDetailSearch());
            case RECIPIENT_TEL_1 -> fulfillmentDto.recipientPhoneNumber1.eq(queryParams.getDetailSearch());
            case RECIPIENT_TEL_2 -> fulfillmentDto.recipientPhoneNumber2.eq(queryParams.getDetailSearch());
            case RECIPIENT_ADDRESS -> fulfillmentDto.recipientAddress.contains(queryParams.getDetailSearch());
            case SHIPPING_TRACKING_NUMBER -> fulfillmentDto.shippingTrackingNumber.eq(queryParams.getDetailSearch());
        };
    }

    private BooleanExpression eqDateSearch(FulfillmentQueryParams queryParams) {
        if (queryParams.getDateSearchParam() == null
                || queryParams.getStartDate() == null
                || queryParams.getEndDate() == null) {
            return null;
        }

        return switch (queryParams.getDateSearchParam()) {
            case PLACED_AT -> fulfillmentDto.placedOn.between(queryParams.getStartDate(), queryParams.getEndDate());
            case DISPATCH_DEADLINE -> fulfillmentDto.dispatchDeadline.between(queryParams.getStartDate(), queryParams.getEndDate());
            case PREFERRED_SHIPS_ON -> fulfillmentDto.preferredShipsOn.between(queryParams.getStartDate(), queryParams.getEndDate());
            case PURCHASED_ON -> fulfillmentDto.purchasedOn.between(queryParams.getStartDate(), queryParams.getEndDate());
            case DISPATCHED_ON -> fulfillmentDto.dispatchedOn.between(queryParams.getStartDate(), queryParams.getEndDate());
            case SHIPPED_ON -> fulfillmentDto.shippedOn.between(queryParams.getStartDate(), queryParams.getEndDate());
            case CONFIRMED_ON -> fulfillmentDto.confirmedOn.between(queryParams.getStartDate(), queryParams.getEndDate());
            case CANCELLED_ON -> fulfillmentDto.cancelledOn.between(queryParams.getStartDate(), queryParams.getEndDate());
            case REFUNDED_ON -> fulfillmentDto.refundedOn.between(queryParams.getStartDate(), queryParams.getEndDate());
        };
    }

    private BooleanExpression eqItemOrderStatus(FulfillmentQueryParams queryParams) {
        if (queryParams.getItemOrderState() == null) return null;

        return fulfillmentDto.itemOrderState.eq(queryParams.getItemOrderState());
    }

    private BooleanExpression eqSalesChannel(FulfillmentQueryParams queryParams) {
        if (queryParams.getSalesChannel() == null) return null;

        return fulfillmentDto.salesChannel.eq(queryParams.getSalesChannel());
    }

    private BooleanExpression eqProductNumber(FulfillmentQueryParams queryParams) {
        if (queryParams.getProductNumber() == null || queryParams.getProductNumber().isBlank()) return null;

        return fulfillmentDto.productNumber.eq(queryParams.getProductNumber());
    }

    private OrderSpecifier<?>[] getOrderSpecifiers(final Sort sort) {

        final ArrayList<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        final OrderSpecifier<String> defaultOrderSpecifier = fulfillmentDto.itemOrderNumber.desc();

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
            return switch (FulfillmentSortParam.valueOf(property)) {
                case SHIPPING_REGION_NAME -> fulfillmentDto.shippingRegionName;
                case PLACED_ON -> fulfillmentDto.placedOn;
                case DISPATCH_DEADLINE -> fulfillmentDto.dispatchDeadline;
                case PREFERRED_SHIPS_ON -> fulfillmentDto.preferredShipsOn;
                case PURCHASED_ON -> fulfillmentDto.purchasedOn;
                case DISPATCHED_ON -> fulfillmentDto.dispatchedOn;
                case SHIPPED_ON -> fulfillmentDto.shippedOn;
                case CANCELLED_ON -> fulfillmentDto.confirmedOn;
                case CONFIRMED_ON -> fulfillmentDto.cancelledOn;
                case REFUNDED_ON -> fulfillmentDto.refundedOn;
            };

        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

}
