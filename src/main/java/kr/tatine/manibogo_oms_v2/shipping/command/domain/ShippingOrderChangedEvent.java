package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ShippingOrderChangedEvent extends Event {

    private final ShippingNumber shippingNumber;

}
