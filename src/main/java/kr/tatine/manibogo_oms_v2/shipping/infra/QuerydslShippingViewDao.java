package kr.tatine.manibogo_oms_v2.shipping.infra;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.tatine.manibogo_oms_v2.shipping.query.ShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.out.port.ShippingViewDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.shipping.command.domain.QShipping.shipping;

@Repository
@RequiredArgsConstructor
public class QuerydslShippingViewDao implements ShippingViewDao {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ShippingView> findAll(Pageable pageable) {

        final List<ShippingView> content = queryFactory.select(
                Projections.constructor(
                    ShippingView.class,
                    shipping.number.shippingNumber.as("shippingNumber"),
                    shipping.state.stringValue().as("shippingState"),
                    shipping.recipient.address.address1.as("address1"),
                    shipping.recipient.address.address2.as("address2"),
                    shipping.recipient.address.zipCode.as("zipCode"),
                    shipping.recipient.name.as("recipientName"),
                    shipping.recipient.phoneNumber1.phoneNumber.as("recipientTel1"),
                    shipping.recipient.phoneNumber2.phoneNumber.as("recipientTel2")
                )
        ).from(shipping).fetch();

        final JPAQuery<Long> countQuery = queryFactory.select(shipping.count()).from(shipping);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchFirst);
    }
}
