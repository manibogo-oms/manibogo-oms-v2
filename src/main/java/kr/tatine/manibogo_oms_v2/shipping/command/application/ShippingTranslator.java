package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.shipping.command.domain.*;
import kr.tatine.manibogo_oms_v2.shipping.query.OrderShippingView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShippingTranslator {

    public Shipping translate(OrderShippingView view) {
        return switch (view.shippingMethod()) {
            case DIRECT -> new DirectShipping(view.shippingNumber(), view.chargeType(), view.recipient(), List.of(new ShippingOrder(view.orderNumber(), view.orderState(), view.productNumber(), view.amount())));
            case PARCEL -> new CourierShipping(view.shippingNumber(), view.chargeType(), view.recipient(), List.of(new ShippingOrder(view.orderNumber(), view.orderState(), view.productNumber(), view.amount())));
        };
    }

}
