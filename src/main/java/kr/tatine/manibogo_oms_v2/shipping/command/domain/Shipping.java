package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@ToString
@Getter(AccessLevel.PROTECTED)
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

    @ElementCollection
    @CollectionTable(name = "shipping_orders", joinColumns = @JoinColumn(name = "shipping_number"))
    private List<ShippingOrder> orders = new ArrayList<>();

    public Shipping(ShippingNumber number, ChargeType chargeType, Recipient recipient, List<ShippingOrder> orders) {
        this.number = number;
        this.chargeType = chargeType;
        this.recipient = recipient;
        addOrders(orders);
    }

    public void dispatch() {}

    public abstract void complete();

    public void revert() {}

    public void bundle(final Shipping shipping) throws CannotBundleShippingException {

        if (!isSameMethod(shipping)) {
            throw new CannotBundleShippingException("mismatch.shipping.method");
        }

        if (!Objects.equals(getChargeType(), shipping.getChargeType())) {
            throw new CannotBundleShippingException("mismatch.shipping.chargeType");
        }

        if (!Objects.equals(getRecipient(), shipping.getRecipient())) {
            throw new CannotBundleShippingException("mismatch.shipping.recipient");
        }

        addOrders(shipping.getOrders());
    }

    public void addOrders(final List<ShippingOrder> orders) {

        final Set<OrderNumber> orderNumbers = orders.stream()
                .map(ShippingOrder::getOrderNumber)
                .collect(Collectors.toSet());

        final Iterator<ShippingOrder> iterator = this.orders.iterator();

        while (iterator.hasNext()) {
            final ShippingOrder order = iterator.next();

            if (!orderNumbers.contains(order.getOrderNumber())) continue;

            iterator.remove();
        }

        this.orders.addAll(orders);
        updateState();
    }

    public void removeOrder(OrderNumber orderNumber) {

        final Iterator<ShippingOrder> iterator = this.orders.iterator();

        while (iterator.hasNext()) {
            final ShippingOrder order = iterator.next();

            if (!orderNumber.equals(order.getOrderNumber())) continue;

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

    protected abstract boolean isSameMethod(final Shipping shipping);


}
