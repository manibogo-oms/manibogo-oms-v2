package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.Customer;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.Recipient;
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

    private Customer customer;

    @Embedded
    private Recipient recipient;

    @Enumerated(EnumType.STRING)
    private SalesChannel salesChannel;

    public Order(OrderNumber number, Customer customer, Recipient recipient, SalesChannel salesChannel) {
        this.number = number;
        this.customer = customer;
        this.recipient = recipient;
        this.salesChannel = salesChannel;
    }

}
