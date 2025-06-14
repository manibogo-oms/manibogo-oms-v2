package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderNumber;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class ItemOrderPlacedEvent extends Event {

    private final String itemOrderNumber;

    private final LocalDateTime itemOrderPlacedAt;

    private final Long totalPrice;

}
