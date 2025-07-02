package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.event.Events;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderStateChangedEvent;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @EmbeddedId
    private OrderNumber number;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Embedded
    private Customer customer;

    @Embedded
    private Recipient recipient;

    @Enumerated(EnumType.STRING)
    private SalesChannel salesChannel;

    @Embedded
    private OrderProduct product;

    @Embedded
    private Shipping shipping;

    @Embedded
    private Memo memo;

    @Embedded
    private TrackingInfo trackingInfo;

    public Order(OrderNumber number, Customer customer, Recipient recipient, SalesChannel salesChannel, OrderProduct product, Shipping shipping, Memo memo) {
        this.number = number;
        this.state = OrderState.PLACED;
        this.customer = customer;
        this.recipient = recipient;
        this.salesChannel = salesChannel;
        this.product = product;
        this.shipping = shipping;
        this.memo = memo;
    }

    public void changeState(OrderState state, LocalDateTime changedAt) {
        final OrderState prevState = this.state;
        setState(state, changedAt);

        if (prevState.equals(this.state)) return;
        Events.raise(new OrderStateChangedEvent(number.getOrderNumber(), prevState.name(), state.name(), changedAt));
    }

    public void proceedState(OrderState state, LocalDateTime changedAt) {
        if (this.state.isAfterOrSame(state)) return;
        changeState(state, changedAt);
    }

    public void changeCustomer(Customer customer) {
        if (Objects.equals(this.customer, customer)) return;
        this.customer = customer;
    }

    public void changeRecipient(Recipient recipient) {
        if (Objects.equals(this.recipient, recipient)) return;
        this.recipient = recipient;
    }

    public void changeShipping(Shipping shipping) {
        if (Objects.equals(this.shipping, shipping)) return;
        this.shipping = shipping;
    }

    public void changeTrackingInfo(TrackingInfo trackingInfo) {
        if (Objects.equals(this.trackingInfo, trackingInfo)) return;
        this.trackingInfo = trackingInfo;
    }

    public void changeMemo(Memo memo) {
        if (Objects.equals(this.memo, memo)) return;
        this.memo = memo;
    }

    private void setState(OrderState state, LocalDateTime changedAt) {
        if (Objects.equals(this.state, state)) return;
        this.state = state;

    }
}
