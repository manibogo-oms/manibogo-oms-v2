package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOrder {

    @EmbeddedId
    private ItemOrderNumber number;

    @Enumerated(EnumType.STRING)
    private ItemOrderState state;

    @Embedded
    private Shipping shipping;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "item_order_state_history",
            joinColumns = @JoinColumn(name = "item_order_number"))
    @OrderColumn(name = "history_index")
    private List<StateHistory> histories = new ArrayList<>();

    public ItemOrder(ItemOrderNumber number, ItemOrderState state, Shipping shipping, List<StateHistory> histories) {
        this.number = number;
        this.state = state;
        this.shipping = shipping;
        this.histories = histories;
    }
}
