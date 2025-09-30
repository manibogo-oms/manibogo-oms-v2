package kr.tatine.manibogo_oms_v2.shipping.query.entity;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ShippingJuso {

    @EmbeddedId
    private ShippingNumber shippingNumber;

    private String jusoCode;

    public ShippingJuso(ShippingNumber shippingNumber, String jusoCode) {
        this.shippingNumber = shippingNumber;
        this.jusoCode = jusoCode;
    }
}
