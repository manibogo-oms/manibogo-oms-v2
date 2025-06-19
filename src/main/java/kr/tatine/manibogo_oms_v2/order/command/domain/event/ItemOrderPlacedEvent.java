package kr.tatine.manibogo_oms_v2.order.command.domain.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
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
