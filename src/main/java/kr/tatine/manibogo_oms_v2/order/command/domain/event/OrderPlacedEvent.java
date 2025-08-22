package kr.tatine.manibogo_oms_v2.order.command.domain.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class OrderPlacedEvent extends Event {

    private final String orderNumber;

    private final LocalDateTime orderPlacedAt;

    public OrderPlacedEvent(OrderNumber orderNumber, LocalDateTime orderPlacedAt) {
        this.orderNumber = orderNumber.getOrderNumber();
        this.orderPlacedAt = orderPlacedAt;
    }
}
