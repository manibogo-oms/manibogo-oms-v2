package kr.tatine.manibogo_oms_v2.shipping.infra.adapter;

import jakarta.persistence.EntityManager;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.shipping.command.application.dto.OrderSnapshot;
import kr.tatine.manibogo_oms_v2.shipping.command.application.port.out.OrderSnapshotQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaOrderSnapshotAdapter implements OrderSnapshotQueryPort {

    private final EntityManager em;

    @Override
    public Optional<OrderSnapshot> findByNumber(OrderNumber orderNumber) {

        return Optional.ofNullable(em.createQuery("""
            SELECT new kr.tatine.manibogo_oms_v2.shipping.command.application.dto.OrderSnapshot(
                    o.number,
                    o.state,
                    o.shippingInfo.shippingBundleNumber,
                    o.shippingInfo.method,
                    o.shippingInfo.chargeType,
                    o.recipient,
                    o.product.productNumber,
                    o.product.amount
                ) FROM Order o WHERE o.number = :orderNumber
        """, OrderSnapshot.class).setParameter("orderNumber", orderNumber).getSingleResult());
    }
}
