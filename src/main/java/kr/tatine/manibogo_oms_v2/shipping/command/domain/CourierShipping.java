package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.Entity;
import lombok.ToString;

@Entity
@ToString
public class CourierShipping extends Shipping {

    private String courierName;

    private String trackingNumber;

    @Override
    public void complete() {}

    public void registerTrackingInfo(final String courierName, final String trackingNumber) {
        this.courierName = courierName;
        this.trackingNumber = trackingNumber;
    }
}
