package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

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

    private String trackingNumber;

    private String companyName;

    public TrackingInfo(String trackingNumber, String companyName) {
        this.trackingNumber = trackingNumber;
        this.companyName = companyName;
    }

}
