package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.event.Events;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderPlacedEvent;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderShippingInfoChangedEvent;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.OrderStateChangedEvent;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @EmbeddedId
    private OrderNumber number;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState state;

    @Embedded
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private SalesChannel salesChannel;

    @Embedded
    private OrderProduct product;

    @Embedded
    private ShippingNumber shippingNumber;

    @Embedded
    private ShippingInfo shippingInfo;

    @Embedded
    private Memo memo;

    @Embedded
    private TrackingInfo trackingInfo;

    private LocalDate dispatchDeadline;

    private LocalDate preferredShippingDate;

    public Order(OrderNumber number, Customer customer, SalesChannel salesChannel, OrderProduct product, ShippingNumber shippingNumber, ShippingInfo shippingInfo, Memo memo, LocalDateTime placedAt, LocalDate dispatchDeadline, LocalDate preferredShippingDate) {
        this.number = number;
        this.state = OrderState.PLACED;
        this.customer = customer;
        this.salesChannel = salesChannel;
        this.product = product;
        this.shippingNumber = shippingNumber;
        setShippingInfo(shippingInfo);
        this.memo = memo;
        this.dispatchDeadline = dispatchDeadline;
        this.preferredShippingDate = preferredShippingDate;

        Events.raise(new OrderPlacedEvent(number, placedAt));
    }

    boolean canBundleShippingWith(Order order) {
        return (number == order.number)
                || (customer.equals(order.customer) && shippingInfo.equals(order.shippingInfo));
    }

    public void changeState(OrderState state, LocalDateTime changedAt) {
        final OrderState prevState = this.state;
        setState(state, changedAt);

        if (prevState.equals(this.state)) return;
        Events.raise(new OrderStateChangedEvent(number.getOrderNumber(), state.name(), changedAt));
    }

    public void proceedState(OrderState state, LocalDateTime changedAt) {
        if (this.state.isAfterOrSame(state)) return;
        changeState(state, changedAt);
    }

    public void changeCustomer(Customer customer) {
        if (Objects.equals(this.customer, customer)) return;
        this.customer = customer;
    }

    public void changeShippingInfo(ShippingInfo shippingInfo) {
        if (Objects.equals(this.shippingInfo, shippingInfo)) return;
        this.shippingInfo = shippingInfo;
    }

    public void changeTrackingInfo(TrackingInfo trackingInfo) {
        if (Objects.equals(this.trackingInfo, trackingInfo)) return;
        this.trackingInfo = trackingInfo;
    }

    public void changeMemo(Memo memo) {
        if (Objects.equals(this.memo, memo)) return;
        this.memo = memo;
    }

    public void changeDispatchDeadline(LocalDate dispatchDeadline) {
        this.dispatchDeadline = dispatchDeadline;
    }

    public void changePreferredShippingDate(LocalDate preferredShippingDate) {
        this.preferredShippingDate = preferredShippingDate;
    }

    private void setState(OrderState state, LocalDateTime changedAt) {
        if (Objects.equals(this.state, state)) return;
        this.state = state;
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (Objects.equals(this.shippingInfo, shippingInfo)) return;
        this.shippingInfo = shippingInfo;
        Events.raise(new OrderShippingInfoChangedEvent(number, shippingNumber, shippingInfo.getMethod(), shippingInfo.getChargeType(), shippingInfo.getRecipient()));
    }
}
