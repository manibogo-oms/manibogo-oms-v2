package kr.tatine.manibogo_oms_v2.variant.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VariantDto {

    private String productNumber;

    private String key;

    private String value;

    private String label;

    public VariantDto(String productNumber, String key, String value, String label) {
        this.productNumber = productNumber;
        this.key = key;
        this.value = value;
        this.label = label;
    }
}
