package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class ItemOrderStateChangedEvent extends Event {

    private final String itemOrderNumber;

    private final String previousStateName;

    private final String newStateName;

    private final LocalDateTime changedAt;

}
