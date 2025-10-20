package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.Recipient;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@DiscriminatorValue("COURIER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourierShipping extends Shipping {

    private String courierName;

    private String trackingNumber;

    public CourierShipping(ShippingNumber number, ChargeType chargeType, Recipient recipient, String customerMessage) {
        super(number, chargeType, recipient, customerMessage);
    }

    @Override
    protected boolean isSameMethod(final ShippingMethod method) {
        return method.equals(ShippingMethod.PARCEL);
    }

    public void registerTrackingInfo(final String courierName, final String trackingNumber) {
        this.courierName = courierName;
        this.trackingNumber = trackingNumber;
    }

}
