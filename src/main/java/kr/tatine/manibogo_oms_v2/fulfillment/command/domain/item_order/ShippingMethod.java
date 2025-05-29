package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShippingMethod {

    DIRECT("직접전달"), PARCEL("택배,등기,소포");

    private final String description;

}
