package kr.tatine.manibogo_oms_v2.shipping.command.application;

import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.*;
import org.springframework.stereotype.Component;

@Component
public class ShippingTranslator {

    public Shipping translate(CreateOrBundleShippingCommand command) {
        return switch (command.method()) {
            case "DIRECT" -> new DirectShipping(getShippingNumber(command), getChargeType(command), getRecipient(command));
            case "PARCEL" -> new CourierShipping(getShippingNumber(command), getChargeType(command), getRecipient(command));
            default -> throw new IllegalStateException("Unexpected value: " + command.method());
        };
    }

    private static ChargeType getChargeType(CreateOrBundleShippingCommand command) {
        return ChargeType.valueOf(command.chargeType());
    }

    private static ShippingNumber getShippingNumber(CreateOrBundleShippingCommand command) {
        return new ShippingNumber(command.shippingNumber());
    }

    private static Recipient getRecipient(CreateOrBundleShippingCommand command) {
        return new Recipient(command.recipientName(), new PhoneNumber(command.recipientTel1()), new PhoneNumber(command.recipientTel2()), new Address(command.address1(), command.address2(), command.zipCode()));
    }

}
