package kr.tatine.manibogo_oms_v2.variant.command.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import kr.tatine.manibogo_oms_v2.common.model.Option;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VariantId implements Serializable {

    @Embedded
    private Option option;

    @Embedded
    private ProductNumber productNumber;

    public VariantId(Option option, ProductNumber productNumber) {
        this.option = option;
        this.productNumber = productNumber;
    }

    public String getInitLabel() {
        return option.getValue();
    }

}
