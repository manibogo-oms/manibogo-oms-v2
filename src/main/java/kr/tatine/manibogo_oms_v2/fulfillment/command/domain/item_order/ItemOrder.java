package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOrder {

    @EmbeddedId
    private ItemOrderNumber number;

    @Enumerated(EnumType.STRING)
    private ItemOrderState state;

    @Embedded
    private Item item;

    @Embedded
    private ShippingInfo shippingInfo;

    @Embedded
    private TrackingInfo trackingInfo;

    private LocalDate preferredShipsOn;

    private LocalDate dispatchDeadline;

    public ItemOrder(ItemOrderNumber number, ItemOrderState state, Item item, ShippingInfo shippingInfo, TrackingInfo trackingInfo, LocalDate preferredShipsOn, LocalDate dispatchDeadline) {
        this.number = number;
        setState(state);
        this.item = item;
        setShippingInfo(shippingInfo);
        setTrackingInfo(trackingInfo);
        setPreferredShipsOn(preferredShipsOn);
        setDispatchDeadline(dispatchDeadline);
    }

    public void changeState(ItemOrderState targetState) {
        setState(targetState);
    }

    public void proceedState(ItemOrderState targetState) {
        if (!this.state.canProceedTo(targetState)) {
            throw new StateAlreadyProceededException();
        }
        setState(targetState);
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
        verifyNotDispatched();
        setDispatchDeadline(dispatchDeadline);
    }

    public void changePreferredShipsOn(LocalDate preferredShipsOn) {
        verifyNotShipped();
        setPreferredShipsOn(preferredShipsOn);
    }

    private void verifyNotDispatched() {
        if (this.state.isAfter(ItemOrderState.DISPATCHED)) {
            throw new AlreadyDispatchedException();
        }
    }

    private void verifyNotShipped() {
        if (this.state.isAfter(ItemOrderState.SHIPPED)) {
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
