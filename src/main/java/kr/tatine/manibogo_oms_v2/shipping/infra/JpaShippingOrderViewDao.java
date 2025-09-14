package kr.tatine.manibogo_oms_v2.shipping.infra;

import jakarta.persistence.EntityManager;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingOrderView;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingOrderViewDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaShippingOrderViewDao implements ShippingOrderViewDao {

    private final EntityManager em;

    private static final String FIND_ALL_BY_SHIPPING_NUMBER_QUERY = """
        SELECT new kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingOrderView(
        s.number, so.orderNumber, so.orderState, p.name, so.amount
        )
        FROM Shipping s
        JOIN s.orders so
        JOIN Product p ON so.productNumber = p.number
        WHERE s.number = :shippingNumber
    """;

    @Override
    public List<ShippingOrderView> findAllByShippingNumber(ShippingNumber shippingNumber) {
        return em.createQuery(FIND_ALL_BY_SHIPPING_NUMBER_QUERY, ShippingOrderView.class).setParameter("shippingNumber", shippingNumber).getResultList();
    }
}
