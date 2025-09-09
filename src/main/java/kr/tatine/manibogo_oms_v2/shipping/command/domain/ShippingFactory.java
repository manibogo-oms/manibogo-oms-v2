package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.Recipient;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShippingFactory {

    public Shipping create(
            ShippingMethod method,
            ShippingNumber shippingNumber,
            ChargeType chargeType,
            Recipient recipient
    ) {
        return switch (method) {
            case DIRECT -> new DirectShipping(shippingNumber, chargeType, recipient);
            case PARCEL -> new CourierShipping(shippingNumber, chargeType, recipient);
        };
    }

}
