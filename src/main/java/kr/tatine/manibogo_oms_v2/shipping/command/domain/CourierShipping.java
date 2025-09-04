package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.Entity;
import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.Recipient;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourierShipping extends Shipping {

    private String courierName;

    private String trackingNumber;

    public CourierShipping(ShippingNumber number, ChargeType chargeType, Recipient recipient, List<ShippingOrder> orders) {
        super(number, chargeType, recipient, orders);
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
