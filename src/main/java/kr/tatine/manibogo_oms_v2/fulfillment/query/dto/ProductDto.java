package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

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

    public ProductDto(String number, String name, Integer priority) {
        this.number = number;
        this.name = name;
        this.priority = priority;
    }
}
