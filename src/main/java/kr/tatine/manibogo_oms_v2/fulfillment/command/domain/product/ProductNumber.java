package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product;

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
public class ProductNumber implements Serializable {

    private String productNumber;

    public ProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

}
