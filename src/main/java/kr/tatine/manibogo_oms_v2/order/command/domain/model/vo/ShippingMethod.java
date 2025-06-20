package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShippingMethod implements Describable {

    DIRECT("직접전달"), PARCEL("택배,등기,소포");

    private final String description;
}
