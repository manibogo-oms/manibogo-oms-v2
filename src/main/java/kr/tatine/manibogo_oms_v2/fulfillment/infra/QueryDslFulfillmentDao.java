package kr.tatine.manibogo_oms_v2.fulfillment.infra;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.FulfillmentDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentDto;
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
    public Page<FulfillmentDto> findAll(Pageable pageable) {

        final List<FulfillmentDto> content = queryFactory
                .selectFrom(fulfillmentDto)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(pageable.getSort()))
                .fetch();

        final JPAQuery<Long> countQuery = queryFactory
                .select(fulfillmentDto.count())
                .from(fulfillmentDto);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
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
        return switch (property) {
            case "shippingRegionName" -> fulfillmentDto.shippingRegionName;
            case "placedOn" -> fulfillmentDto.placedOn;
            case "dispatchDeadline" -> fulfillmentDto.dispatchDeadline;
            case "preferredShipsOn" -> fulfillmentDto.preferredShipsOn;
            case "purchasedOn" -> fulfillmentDto.purchasedOn;
            case "dispatchedOn" -> fulfillmentDto.dispatchedOn;
            case "shippedOn" -> fulfillmentDto.shippedOn;
            case "confirmedOn" -> fulfillmentDto.confirmedOn;
            case "cancelledOn" -> fulfillmentDto.cancelledOn;
            case "refundedOn" -> fulfillmentDto.refundedOn;
            default -> null;
        };
    }

}
