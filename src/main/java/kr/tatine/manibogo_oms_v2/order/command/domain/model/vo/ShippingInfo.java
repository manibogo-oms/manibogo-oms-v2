package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.*;
import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import lombok.*;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingInfo {

    private ShippingNumber shippingBundleNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_method")
    private ShippingMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_charge_type")
    private ChargeType chargeType;

    public ShippingInfo(ShippingNumber shippingBundleNumber, ShippingMethod method, ChargeType chargeType) {
        this.shippingBundleNumber = shippingBundleNumber;
        this.method = method;
        this.chargeType = chargeType;
    }
}
