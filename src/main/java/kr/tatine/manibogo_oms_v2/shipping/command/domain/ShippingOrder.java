package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import lombok.*;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingOrder {

    private OrderNumber orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    private ProductNumber productNumber;

    private Integer amount;

    public ShippingOrder(OrderNumber orderNumber, OrderState orderState, ProductNumber productNumber, Integer amount) {
        this.orderNumber = orderNumber;
        this.orderState = orderState;
        this.productNumber = productNumber;
        this.amount = amount;
    }
}
