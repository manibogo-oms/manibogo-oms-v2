package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.common.model.Option;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    private ProductNumber productNumber;


    @AttributeOverrides({
        @AttributeOverride(name = "key", column = @Column(name = "option_key1")),
        @AttributeOverride(name = "value", column = @Column(name = "option_value1"))
    })
    private Option option1;

    @AttributeOverrides({
            @AttributeOverride(name = "key", column = @Column(name = "option_key2")),
            @AttributeOverride(name = "value", column = @Column(name = "option_value2"))
    })
    private Option option2;

    @AttributeOverrides({
            @AttributeOverride(name = "key", column = @Column(name = "option_key3")),
            @AttributeOverride(name = "value", column = @Column(name = "option_value3"))
    })
    private Option option3;

    private Integer amount;

    @AttributeOverride(name = "value", column = @Column(name = "final_price"))
    private Money price;

    public OrderProduct(ProductNumber productNumber, List<Option> options, Integer amount, Money price) {
        this.productNumber = productNumber;
        if (!options.isEmpty()) {
            this.option1 = options.get(0);
        }
        if (options.size() > 1) {
            this.option2 = options.get(1);
        }
        if (options.size() > 2) {
            this.option3 = options.get(2);
        }
        this.amount = amount;
        this.price = price;
    }

}
