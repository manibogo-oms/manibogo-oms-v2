package kr.tatine.manibogo_oms_v2.common.model;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingNumber implements Serializable {

    @Column(name = "shipping_number")
    private String shippingNumber;

    public ShippingNumber(String shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

    public static ShippingNumber random() {
        return new ShippingNumber(TSID.Factory.getTsid().toString());
    }

}
