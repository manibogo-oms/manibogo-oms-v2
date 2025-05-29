package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @EmbeddedId
    private ProductNumber productNumber;

    private String name;

    @Embedded
    private Priority priority;

    public Product(ProductNumber productNumber, String name, Priority priority) {
        this.productNumber = productNumber;
        this.name = name;
        this.priority = priority;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePriority(Priority priority) {
        this.priority = priority;
    }
}
