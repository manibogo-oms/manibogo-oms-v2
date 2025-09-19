package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.ShippingQuery;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingOrderAggView;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingPageView;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingOrderAggQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingViewQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShippingQueryService {

    private final ShippingViewQueryPort viewQueryPort;

    private final ShippingOrderAggQueryPort orderAggQueryPort;

    @Transactional(readOnly = true)
    public Page<ShippingPageView> findAll(ShippingQuery filter, Pageable pageable) {

        final Page<ShippingView> viewPage = viewQueryPort.findAll(filter, pageable);

        final List<ShippingNumber> shippingNumbers = viewPage.getContent()
                .stream().map(ShippingView::shippingNumber)
                .toList();

        final Map<ShippingNumber, ShippingOrderAggView> orderAggViewMap = new HashMap<>();

        for (final ShippingOrderAggView orderAggView : orderAggQueryPort.findByShippingNumbers(shippingNumbers)) {
            orderAggViewMap.put(orderAggView.getShippingNumber(), orderAggView);
        }

        return viewPage.map(view -> composite(view, orderAggViewMap.get(view.shippingNumber())));
    }

    private static ShippingPageView composite(
            ShippingView view, ShippingOrderAggView orderAggView) {

        return new ShippingPageView(
                view.shippingNumber(),
                view.shippingMethod(),
                view.shippingState(),
                orderAggView != null ? orderAggView.getPrimaryOrderNumber() : null,
                orderAggView != null ? orderAggView.getPrimaryOrderState() : null,
                orderAggView != null ? orderAggView.getPrimaryProductName() : null,
                orderAggView != null ? orderAggView.getPrimaryProductQuantity() : null,
                orderAggView != null ? orderAggView.getTotalOrderCount() : null,
                orderAggView != null ? orderAggView.getTotalQuantity() : null,
                view.sido(),
                view.sigungu(),
                view.address1(),
                view.address2(),
                view.zipCode(),
                view.recipientName(),
                view.recipientTel1(),
                view.recipientTel2()
        );
    }

}
