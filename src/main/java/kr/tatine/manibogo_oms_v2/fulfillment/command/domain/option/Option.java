package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNumber;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Option {

    @EmbeddedId
    private OptionId id;

    private ProductNumber productNumber;

    private String label;

    public Option(OptionId id, ProductNumber productNumber, String label) {
        this.id = id;
        this.productNumber = productNumber;
        this.label = label;
    }
}
