package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.shipping.command.domain.*;
import org.springframework.stereotype.Component;

@Component
public class ShippingTranslator {

    public Shipping translate(CreateOrBundleShippingCommand command) {
        return switch (command.shippingMethod()) {
            case DIRECT -> new DirectShipping(command.shippingNumber(), command.chargeType(), command.recipient());
            case PARCEL -> new CourierShipping(command.shippingNumber(), command.chargeType(), command.recipient());
        };
    }

}
