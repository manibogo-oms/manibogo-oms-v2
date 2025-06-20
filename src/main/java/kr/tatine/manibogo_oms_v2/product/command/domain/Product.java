package kr.tatine.manibogo_oms_v2.product.command.domain;

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
    private ProductNumber number;

    private String name;

    @Embedded
    private Priority priority;

    private Boolean isEnabled;

    public Product(ProductNumber number, String name, Priority priority) {
        this.number = number;
        this.name = name;
        this.priority = priority;
        this.isEnabled = true;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePriority(Priority priority) {
        this.priority = priority;
    }

    public void changeIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    String getName() {
        return name;
    }
}
