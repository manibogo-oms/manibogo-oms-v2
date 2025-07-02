package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private ShippingMethod method;

    @Enumerated(EnumType.STRING)
    private ChargeType chargeType;

    private LocalDate dispatchDeadline;

    private LocalDate preferredShippingDate;

    public Shipping(ShippingMethod method) {
        this.method = method;
    }

    public Shipping(ShippingMethod method, ChargeType chargeType, LocalDate dispatchDeadline, LocalDate preferredShippingDate) {
        this.method = method;
        this.chargeType = chargeType;
        this.dispatchDeadline = dispatchDeadline;
        this.preferredShippingDate = preferredShippingDate;
    }
}
