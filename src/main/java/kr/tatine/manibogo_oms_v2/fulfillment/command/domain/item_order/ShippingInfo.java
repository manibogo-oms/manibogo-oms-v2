package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.SalesChannel;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.SalesChannelNotFoundException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingInfo {

    @Enumerated(EnumType.STRING)
    private ShippingMethod method;

    @Enumerated(EnumType.STRING)
    private ChargeType chargeType;

    public ShippingInfo(ShippingMethod method, ChargeType chargeType) {
        this.method = method;
        this.chargeType = chargeType;
    }


}
