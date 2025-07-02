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

    @ElementCollection
    @OrderColumn(name = "option_seq")
    @CollectionTable(name = "order_product_option", joinColumns = @JoinColumn(name = "order_number"))
    private List<Option> options;

    private Integer amount;

    @AttributeOverride(name = "value", column = @Column(name = "final_price"))
    private Money price;

    public OrderProduct(ProductNumber productNumber, List<Option> options, Integer amount, Money price) {
        this.productNumber = productNumber;
        this.options = options;
        this.amount = amount;
        this.price = price;
    }

}
