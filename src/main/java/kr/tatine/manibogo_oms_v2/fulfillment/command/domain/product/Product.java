package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

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

    public void changeName(ProductRepository repository, String name) {
        if (Objects.equals(this.name, name)) return;

        if (repository.countByName(name) > 0) {
            throw new ProductNameDuplicatedException();
        }

        this.name = name;
    }

    public void changePriority(Priority priority) {
        this.priority = priority;
    }

    public void changeIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

}
