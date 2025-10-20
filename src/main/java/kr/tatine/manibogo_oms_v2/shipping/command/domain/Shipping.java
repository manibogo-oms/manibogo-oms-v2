package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.event.Events;
import kr.tatine.manibogo_oms_v2.common.model.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@ToString
@Getter(AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Shipping {

    @EmbeddedId
    private ShippingNumber number;

    @Enumerated(EnumType.STRING)
    private ShippingState state = ShippingState.PREPARING;;

    @Enumerated(EnumType.STRING)
    private ChargeType chargeType;
 
    @Embedded
    private Recipient recipient;

    private String customerMessage;

    private String adminMemo;

    @ElementCollection
    @CollectionTable(name = "shipping_orders", joinColumns = @JoinColumn(name = "shipping_number"))
    private List<ShippingOrder> orders = new ArrayList<>();

    public Shipping(ShippingNumber number, ChargeType chargeType, Recipient recipient, String customerMessage) {
        this.number = number;
        this.chargeType = chargeType;
        this.recipient = recipient;
        this.customerMessage = customerMessage;

        Events.raise(new ShippingCreatedEvent(number));
    }

    public void bundle(ShippingMethod method, ChargeType chargeType, Recipient recipient) throws CannotBundleShippingException {

        if (!isSameMethod(method)) {
            throw new CannotBundleShippingException("mismatch.shipping.method");
        }

        if (!Objects.equals(getChargeType(), chargeType)) {
            throw new CannotBundleShippingException("mismatch.shipping.chargeType");
        }

        if (!Objects.equals(getRecipient(), recipient)) {
            throw new CannotBundleShippingException("mismatch.shipping.recipient");
        }
    }

    public void addOrder(final ShippingOrder newOrder) {
        deleteExistOrder(newOrder.getOrderNumber());
        this.orders.add(newOrder);
        updateState();

        Events.raise(new ShippingOrderChangedEvent(number));
    }

    public void removeOrder(OrderNumber orderNumber) {
        deleteExistOrder(orderNumber);
        updateState();

        Events.raise(new ShippingOrderChangedEvent(number));
    }

    private void deleteExistOrder(OrderNumber orderNumber) {
        final Iterator<ShippingOrder> iterator = this.orders.iterator();

        while (iterator.hasNext()) {
            final ShippingOrder order = iterator.next();

            if (!order.getOrderNumber().equals(orderNumber)) {
                continue;
            }

            iterator.remove();
        }
    }

    private void updateState() {

        if (this.orders.isEmpty()) {
            this.state = ShippingState.CANCELLED;
            return;
        }

        for (ShippingOrder order : orders) {
            if (order.getOrderState() == OrderState.REFUNDED) {
                this.state = ShippingState.RETURNED;
                return;
            }
            if (order.getOrderState() == OrderState.PLACED || order.getOrderState() == OrderState.PURCHASED) {
                this.state = ShippingState.PREPARING;
                return;
            }
        }

        boolean isAllCancelled = true;
        for (ShippingOrder order : orders) {
            if (order.getOrderState().isBefore(OrderState.CANCELLED)) {
                isAllCancelled = false;
                break;
            }
        }
        if (isAllCancelled) {
            this.state = ShippingState.CANCELLED;
            return;
        }

        boolean isAllShipped = true;
        for (ShippingOrder order : orders) {
            if (order.getOrderState().isBefore(OrderState.SHIPPED)) {
                isAllShipped = false;
                break;
            }
        }
        if (isAllShipped) {
            this.state = ShippingState.COMPLETED;
            return;
        }

        boolean isAllDispatched = true;

        for (ShippingOrder order : orders) {
            if (order.getOrderState().isBefore(OrderState.DISPATCHED)) {
                isAllDispatched = false;
                break;
            }
        }

        if (isAllDispatched) {
            this.state = ShippingState.READY_TO_SHIP;
            return;
        }

    }

    protected abstract boolean isSameMethod(final ShippingMethod method);


}
