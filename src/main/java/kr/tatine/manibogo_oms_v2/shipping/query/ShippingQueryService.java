package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.ShippingFilter;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingOrderAggView;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingPageView;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingOrderAggViewDao;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingViewDao;
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

    private final ShippingViewDao viewDao;

    private final ShippingOrderAggViewDao orderAggViewDao;

    @Transactional(readOnly = true)
    public Page<ShippingPageView> findAll(ShippingFilter filter, Pageable pageable) {

        final Page<ShippingView> viewPage = viewDao.findAll(filter, pageable);

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
