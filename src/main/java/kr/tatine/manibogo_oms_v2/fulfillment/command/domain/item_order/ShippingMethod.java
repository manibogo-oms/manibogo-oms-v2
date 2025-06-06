package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.SalesChannelNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShippingMethod {

    DIRECT("직접전달"), PARCEL("택배,등기,소포");

    private final String description;

    public static ShippingMethod fromDescription(String description) {
        for (ShippingMethod shippingMethod : ShippingMethod.values()) {
            if (shippingMethod.description.equals(description)) {
                return shippingMethod;
            }
        }

        throw new SalesChannelNotFoundException();
    }
}
