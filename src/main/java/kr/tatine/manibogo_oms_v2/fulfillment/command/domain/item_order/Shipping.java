package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shipping {

    @Enumerated
    private ShippingMethod method;

    private LocalDate preferredShipsOn;

    private LocalDate dispatchDeadline;

    private String companyName;

    private String trackingNumber;

    public Shipping(ShippingMethod method, LocalDate preferredShipsOn, LocalDate dispatchDeadline, String companyName, String trackingNumber) {
        this.method = method;
        this.preferredShipsOn = preferredShipsOn;
        this.dispatchDeadline = dispatchDeadline;
        this.companyName = companyName;
        this.trackingNumber = trackingNumber;
    }

}
