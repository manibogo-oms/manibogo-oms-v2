package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import lombok.*;

@Getter(AccessLevel.PACKAGE)
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingOrder {

    private OrderNumber orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public ShippingOrder(OrderNumber orderNumber, OrderState orderState) {
        this.orderNumber = orderNumber;
        this.orderState = orderState;
    }

}
