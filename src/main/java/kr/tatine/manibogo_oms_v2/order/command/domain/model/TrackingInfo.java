package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrackingInfo {

    @Column(name = "shipping_tracking_number")
    private String trackingNumber;

    @Column(name = "shipping_company_name")
    private String companyName;

    public TrackingInfo(String trackingNumber, String companyName) {
        this.trackingNumber = trackingNumber;
        this.companyName = companyName;
    }
}
