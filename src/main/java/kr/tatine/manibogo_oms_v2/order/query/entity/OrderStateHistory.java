package kr.tatine.manibogo_oms_v2.order.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderStateHistory {

    @Id
    private String orderNumber;

    private LocalDateTime placedAt;

    private LocalDateTime purchasedAt;

    private LocalDateTime dispatchedAt;

    private LocalDateTime shippedAt;

    private LocalDateTime confirmedAt;

    private LocalDateTime cancelledAt;

    private LocalDateTime refundedAt;

    public OrderStateHistory(String orderNumber, LocalDateTime placedAt) {
        this.orderNumber = orderNumber;
        this.placedAt = placedAt;
    }

    // TODO: Event Fallback 처리
    public void changeHistory(final String orderState, final LocalDateTime changedAt) {

        switch (orderState.toUpperCase()) {
            case "PLACED" -> this.placedAt = changedAt;
            case "PURCHASED" -> this.purchasedAt = changedAt;
            case "DISPATCHED" -> this.dispatchedAt = changedAt;
            case "SHIPPED" -> this.shippedAt = changedAt;
            case "CONFIRMED" -> this.confirmedAt = changedAt;
            case "CANCELLED" -> this.cancelledAt = changedAt;
            case "REFUNDED" -> this.refundedAt = changedAt;
            default -> throw new IllegalStateException("Unexpected value: " + orderState);
        }
    }
}
