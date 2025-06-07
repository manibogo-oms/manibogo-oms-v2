package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.event.Events;
import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.event.ItemOrderPlacedEvent;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.*;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionId;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.AlreadyDispatchedException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.AlreadyShippedException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.StateAlreadyProceededException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNumber;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @CollectionTable(name = "item_order_option", joinColumns = @JoinColumn(name = "item_order_number"))
    private List<OptionId> optionIds;

    private Integer amount;

    @AttributeOverride(name = "value", column = @Column(name = "total_price"))
    private Money totalPrice;

    @Embedded
    private ShippingInfo shippingInfo;

    @Embedded
    private TrackingInfo trackingInfo;

    private LocalDate preferredShipsOn;

    private LocalDate dispatchDeadline;

    public static ItemOrder place(
            ItemOrderNumber number,
            OrderNumber orderNumber,
            LocalDateTime placedAt,
            ProductNumber productNumber,
            List<OptionId> optionIds,
            Integer amount,
            Money totalPrice,
            ShippingInfo shippingInfo,
            LocalDate dispatchDeadline
    ) {

        Events.raise(new ItemOrderPlacedEvent(number, placedAt));

        return new ItemOrder(
                number,
                orderNumber,
                ItemOrderState.PLACED,
                productNumber,
                optionIds,
                amount,
                totalPrice,
                shippingInfo,
                dispatchDeadline
        );
    }

    private ItemOrder(ItemOrderNumber number, OrderNumber orderNumber, ItemOrderState state, ProductNumber productNumber, List<OptionId> optionIds, Integer amount, Money totalPrice, ShippingInfo shippingInfo, LocalDate dispatchDeadline) {
        this.number = number;
        this.orderNumber = orderNumber;

        setState(state);

        this.productNumber = productNumber;
        this.optionIds = optionIds;
        this.amount = amount;
        this.totalPrice = totalPrice;

        setShippingInfo(shippingInfo);
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

    private void setState(ItemOrderState stateInfo) {
        this.state = stateInfo;
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
