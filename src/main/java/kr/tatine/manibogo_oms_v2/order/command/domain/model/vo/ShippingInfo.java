package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import kr.tatine.manibogo_oms_v2.common.model.Recipient;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import lombok.*;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingInfo {

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_method")
    private ShippingMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_charge_type")
    private ChargeType chargeType;

    @Embedded
    private Recipient recipient;

    public ShippingInfo(ShippingMethod method, ChargeType chargeType, Recipient recipient) {
        this.method = method;
        this.chargeType = chargeType;
        this.recipient = recipient;
    }
}
