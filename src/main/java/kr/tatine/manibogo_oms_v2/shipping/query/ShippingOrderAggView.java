package kr.tatine.manibogo_oms_v2.shipping.query;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingOrderAggView {

    @Id
    private ShippingNumber shippingNumber;

    private String primaryProductName;

    private Integer primaryProductQuantity;

    @Enumerated(EnumType.STRING)
    private OrderState primaryOrderState;

    private Integer totalQuantity;

    private Integer totalOrderCount;

    public ShippingOrderAggView(ShippingNumber shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

}
