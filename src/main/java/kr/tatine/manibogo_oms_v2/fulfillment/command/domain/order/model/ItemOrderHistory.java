package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOrderHistory {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private ItemOrderNumber itemOrderNumber;

    @Enumerated(EnumType.STRING)
    private ItemOrderState previousState;

    @Enumerated(EnumType.STRING)
    private ItemOrderState newState;

    private LocalDateTime changedAt;

    public ItemOrderHistory(
            ItemOrderNumber itemOrderNumber,
            ItemOrderState previousState,
            ItemOrderState newState,
            LocalDateTime changedAt) {

        this.itemOrderNumber = itemOrderNumber;
        this.previousState = previousState;
        this.newState = newState;
        this.changedAt = changedAt;
    }

}
