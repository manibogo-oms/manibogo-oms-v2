package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.ShippingOrderAggView;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.ShippingPageView;
import kr.tatine.manibogo_oms_v2.shipping.query.out.port.ShippingOrderAggViewDao;
import kr.tatine.manibogo_oms_v2.shipping.query.out.port.ShippingViewDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShippingQueryService {

    private final ShippingViewDao viewDao;

    private final ShippingOrderAggViewDao orderAggViewDao;


    public Page<ShippingPageView> findAll(Pageable pageable) {

        final Page<ShippingView> viewPage = viewDao.findAll(pageable);

        final List<ShippingNumber> shippingNumbers = viewPage.getContent()
                .stream().map(ShippingView::shippingNumber)
                .toList();

        final Map<ShippingNumber, ShippingOrderAggView> orderAggViewMap = new HashMap<>();

        for (final ShippingOrderAggView orderAggView : orderAggViewDao.findByShippingNumbers(shippingNumbers)) {
            orderAggViewMap.put(orderAggView.getShippingNumber(), orderAggView);
        }

        return viewPage.map(view -> composite(view, orderAggViewMap.get(view.shippingNumber())));
    }

    private static ShippingPageView composite(
            ShippingView view, ShippingOrderAggView orderAggView) {

        return new ShippingPageView(
                view.shippingNumber(),
                view.shippingState(),
                orderAggView != null ? orderAggView.getPrimaryOrderNumber() : null,
                orderAggView != null ? orderAggView.getPrimaryOrderState() : null,
                orderAggView != null ? orderAggView.getPrimaryProductName() : null,
                orderAggView != null ? orderAggView.getPrimaryProductQuantity() : null,
                orderAggView != null ? orderAggView.getTotalOrderCount() : null,
                orderAggView != null ? orderAggView.getTotalQuantity() : null,
                "",
                "",
                view.address1(),
                view.address2(),
                view.zipCode(),
                view.recipientName(),
                view.recipientTel1(),
                view.recipientTel2()
        );
    }

}
