package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingBundleNumber implements Serializable {

    private String shippingBundleNumber;

    public ShippingBundleNumber(String shippingBundleNumber) {
        this.shippingBundleNumber = shippingBundleNumber;
    }

    public static ShippingBundleNumber random() {
        return new ShippingBundleNumber(TSID.Factory.getTsid().toString());
    }

}
