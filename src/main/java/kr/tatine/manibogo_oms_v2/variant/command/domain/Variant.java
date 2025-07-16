package kr.tatine.manibogo_oms_v2.variant.command.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Variant {

    @EmbeddedId
    private VariantId variantId;

    private String label;

    public Variant(VariantId variantId, String label) {
        this.variantId = variantId;
        this.label = label;
    }

    public Variant(VariantId variantId) {
        this(variantId, variantId.getInitLabel());
    }

    public void changeLabel(String label) {
        this.label = label;
    }

}
