package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateShippingOrderAggService {

    private final ShippingOrderAggViewDao shippingOrderAggViewDao;

    private final ShippingOrderViewDao shippingOrderViewDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(ShippingNumber shippingNumber) {

        final List<ShippingOrderView> viewList =
                shippingOrderViewDao.findAllByShippingNumber(shippingNumber);

        final ShippingOrderAggView orderAgg = new ShippingOrderAggView(shippingNumber);

        orderAgg.setTotalOrderCount(viewList.size());
        orderAgg.setTotalQuantity(viewList.stream().map(ShippingOrderView::quantity).reduce(0, Integer::sum));

        findPrimaryOrder(viewList).ifPresent(primaryOrder -> {
            orderAgg.setPrimaryProductName(primaryOrder.productName());
            orderAgg.setPrimaryOrderState(primaryOrder.orderState());
            orderAgg.setPrimaryProductQuantity(primaryOrder.quantity());
        });

        shippingOrderAggViewDao.save(orderAgg);
    }

    private static Optional<ShippingOrderView> findPrimaryOrder(List<ShippingOrderView> viewList) {

        if (viewList.isEmpty()) return Optional.empty();

        ShippingOrderView primaryOrder = viewList.get(0);

        for (ShippingOrderView order : viewList) {
            if (order.orderState().isBefore(primaryOrder.orderState())) continue;
            if (order.quantity() <= primaryOrder.quantity()) continue;
            primaryOrder = order;
        }
        return Optional.of(primaryOrder);
    }
}
