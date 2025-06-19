package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.event.Events;
import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.ItemOrderPlacedEvent;
import kr.tatine.manibogo_oms_v2.order.command.domain.event.ItemOrderStateChangedEvent;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyDispatchedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyShippedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.CannotProceedToTargetStateException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.StateAlreadyProceededException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.*;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import kr.tatine.manibogo_oms_v2.product.command.domain.VariantId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOrder {

    @EmbeddedId
    private ItemOrderNumber number;

    @Embedded
    private OrderNumber orderNumber;

    @Enumerated(EnumType.STRING)
    private ItemOrderState state;

    private ProductNumber productNumber;

    @ElementCollection
    @OrderColumn(name = "option_seq")
    @CollectionTable(name = "item_order_variant", joinColumns = @JoinColumn(name = "item_order_number"))
    private List<VariantId> variants;

    private Integer amount;

    @AttributeOverride(name = "value", column = @Column(name = "total_price"))
    private Money totalPrice;

    @Embedded
    private ShippingInfo shippingInfo;

    @Embedded
    private TrackingInfo trackingInfo;

    private LocalDate preferredShipsOn;

    private LocalDate dispatchDeadline;

    @Embedded
    private ItemOrderNote note;

    public static ItemOrder place(
            ItemOrderNumber number,
            OrderNumber orderNumber,
            LocalDateTime placedAt,
            ProductNumber productNumber,
            List<VariantId> variantIds,
            Integer amount,
            Money totalPrice,
            ShippingInfo shippingInfo,
            LocalDate dispatchDeadline
    ) {

        Events.raise(new ItemOrderPlacedEvent(
                number.getItemOrderNumber(), placedAt, totalPrice.getValue()));

        return new ItemOrder(
                number,
                orderNumber,
                ItemOrderState.PLACED,
                productNumber,
                variantIds,
                amount,
                totalPrice,
                shippingInfo,
                dispatchDeadline
        );
    }

    private ItemOrder(ItemOrderNumber number, OrderNumber orderNumber, ItemOrderState state, ProductNumber productNumber, List<VariantId> variantIds, Integer amount, Money totalPrice, ShippingInfo shippingInfo, LocalDate dispatchDeadline) {
        this.number = number;
        this.orderNumber = orderNumber;

        setState(state);

        this.productNumber = productNumber;
        this.variants = variantIds;
        this.amount = amount;
        this.totalPrice = totalPrice;

        setShippingInfo(shippingInfo);
        setPreferredShipsOn(preferredShipsOn);
        setDispatchDeadline(dispatchDeadline);
    }

    public void changeState(ItemOrderState targetState, LocalDateTime changedAt) {
        final ItemOrderState prevState = this.state;

        setState(targetState);

        if (prevState.equals(this.state)) return;

        Events.raise(new ItemOrderStateChangedEvent(
                number.getItemOrderNumber(),
                prevState.name(),
                this.state.name(),
                changedAt));
    }

    public void proceedState(ItemOrderState targetState, LocalDateTime changedAt) {
        if (targetState.isBefore(ItemOrderState.PURCHASED)) {
            throw new CannotProceedToTargetStateException();
        }

        if (!this.state.canProceedTo(targetState)) {
            throw new StateAlreadyProceededException();
       }
       changeState(targetState, changedAt);
    }

    public void changeShippingInfo(ShippingInfo shippingInfo) {
        verifyNotDispatched();
        setShippingInfo(shippingInfo);
    }

    public void changeTrackingInfo(TrackingInfo trackingInfo) {
        verifyNotShipped();
        setTrackingInfo(trackingInfo);
    }

    public void changeDispatchDeadline(LocalDate dispatchDeadline) {
        if (Objects.equals(this.dispatchDeadline, dispatchDeadline)) return;

        verifyNotDispatched();
        setDispatchDeadline(dispatchDeadline);
    }

    public void changePreferredShipsOn(LocalDate preferredShipsOn) {
        if (Objects.equals(this.preferredShipsOn, preferredShipsOn)) return;

        verifyNotShipped();
        setPreferredShipsOn(preferredShipsOn);
    }

    public void changeNote(ItemOrderNote itemOrderNote) {
        this.note = itemOrderNote;
    }

    private void verifyNotDispatched() {
        if (this.state.isAfterOrSame(ItemOrderState.DISPATCHED)) {
            throw new AlreadyDispatchedException();
        }
    }

    private void verifyNotShipped() {
        if (this.state.isAfterOrSame(ItemOrderState.SHIPPED)) {
            throw new AlreadyShippedException();
        }
    }

    private void setState(ItemOrderState state) {
        this.state = state;
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    private void setTrackingInfo(TrackingInfo trackingInfo) {
        this.trackingInfo = trackingInfo;
    }

    private void setDispatchDeadline(LocalDate dispatchDeadline) {
        this.dispatchDeadline = dispatchDeadline;
    }

    private void setPreferredShipsOn(LocalDate preferredShipsOn) {
        this.preferredShipsOn = preferredShipsOn;
    }
}
