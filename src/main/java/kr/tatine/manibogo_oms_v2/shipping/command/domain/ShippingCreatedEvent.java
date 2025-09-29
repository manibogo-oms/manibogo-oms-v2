package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper=true)
public class ShippingCreatedEvent extends Event {

    private final ShippingNumber shippingNumber;

    public ShippingCreatedEvent(ShippingNumber shippingNumber) {
        this.shippingNumber = shippingNumber;
    }
}
