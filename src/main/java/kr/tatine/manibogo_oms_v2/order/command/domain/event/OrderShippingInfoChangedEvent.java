package kr.tatine.manibogo_oms_v2.order.command.domain.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class OrderShippingInfoChangedEvent extends Event {

    private final OrderNumber orderNumber;

    public OrderShippingInfoChangedEvent(OrderNumber orderNumber) {
        this.orderNumber = orderNumber;
    }
}
