package kr.tatine.manibogo_oms_v2.order.command.domain.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.common.model.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class OrderShippingInfoChangedEvent extends Event {

    private final OrderNumber orderNumber;

    private final ShippingNumber shippingNumber;

    private final ShippingMethod method;

    private final ChargeType chargeType;

    private final Recipient recipient;

    public OrderShippingInfoChangedEvent(OrderNumber orderNumber, ShippingNumber shippingNumber, ShippingMethod method, ChargeType chargeType, Recipient recipient) {
        this.orderNumber = orderNumber;
        this.shippingNumber = shippingNumber;
        this.method = method;
        this.chargeType = chargeType;
        this.recipient = recipient;
    }
}
