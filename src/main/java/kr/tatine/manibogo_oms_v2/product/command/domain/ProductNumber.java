package kr.tatine.manibogo_oms_v2.product.command.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
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
