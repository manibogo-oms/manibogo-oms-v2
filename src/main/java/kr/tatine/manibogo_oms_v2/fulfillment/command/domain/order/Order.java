package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @EmbeddedId
    private OrderNumber number;

    @Embedded
    @AttributeOverride(
            name = "name",
            column = @Column(name = "customer_name"))
    private Customer customer;

    @Embedded
    private Recipient recipient;

    @Enumerated(EnumType.STRING)
    private OrderLocation location;

    public Order(OrderNumber number, Customer customer, Recipient recipient, OrderLocation location) {
        this.number = number;
        this.customer = customer;
        this.recipient = recipient;
        this.location = location;
    }
}
