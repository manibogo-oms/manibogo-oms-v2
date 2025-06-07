package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNumber;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ItemOrderPlacedEvent extends Event {

    private String itemOrderNumber;

    private LocalDateTime itemOrderPlacedAt;

    public ItemOrderPlacedEvent(String itemOrderNumber, LocalDateTime itemOrderPlacedAt) {
        this.itemOrderNumber = itemOrderNumber;
        this.itemOrderPlacedAt = itemOrderPlacedAt;
    }
}
