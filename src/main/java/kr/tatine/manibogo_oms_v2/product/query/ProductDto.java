package kr.tatine.manibogo_oms_v2.product.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@NoArgsConstructor
public class ProductDto {

    private String number;

    private String name;

    private Integer priority;

    private Boolean isEnabled;

    public ProductDto(String number, String name, Integer priority, Boolean isEnabled) {
        this.number = number;
        this.name = name;
        this.priority = priority;
        this.isEnabled = isEnabled;
    }
}
