package kr.tatine.manibogo_oms_v2.shipping.infra;

import jakarta.persistence.EntityManager;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.OrderShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.OrderShippingViewDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaOrderShippingViewDao implements OrderShippingViewDao {

    private final EntityManager em;

    @Override
    public Optional<OrderShippingView> findByOrderNumber(OrderNumber orderNumber) {

        return Optional.ofNullable(em.createQuery("""
            SELECT new kr.tatine.manibogo_oms_v2.shipping.query.OrderShippingView(
                    o.number,
                    o.state,
                    o.shippingInfo.shippingBundleNumber,
                    o.shippingInfo.method,
                    o.shippingInfo.chargeType,
                    o.recipient,
                    o.product.productNumber,
                    o.product.amount
                ) FROM Order o WHERE o.number = :orderNumber
        """, OrderShippingView.class).setParameter("orderNumber", orderNumber).getSingleResult());
    }
}
