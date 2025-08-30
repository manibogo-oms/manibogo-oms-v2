package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourierShipping extends Shipping {

    private String courierName;

    private String trackingNumber;

    public CourierShipping(ShippingNumber number, ChargeType chargeType, Recipient recipient) {
        super(number, chargeType, recipient);
    }

    @Override
    public void complete() {}

    @Override
    protected boolean isSameMethod(Shipping shipping) {
        return shipping instanceof CourierShipping;
    }

    public void registerTrackingInfo(final String courierName, final String trackingNumber) {
        this.courierName = courierName;
        this.trackingNumber = trackingNumber;
    }

}
