package kr.tatine.manibogo_oms_v2.shipping.query.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingOrderAgg {

    @EmbeddedId
    private ShippingNumber shippingNumber;

    private OrderNumber primaryOrderNumber;

    @Enumerated(EnumType.STRING)
    private OrderState primaryOrderState;

    private String primaryOrderProduct;

    private Integer primaryOrderQuantity;

    private Integer totalOrderQuantity;

    private Integer totalOrderCount;

    public ShippingOrderAgg(ShippingNumber shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

}
