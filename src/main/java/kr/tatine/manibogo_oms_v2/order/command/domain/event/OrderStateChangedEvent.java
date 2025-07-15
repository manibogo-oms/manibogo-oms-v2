package kr.tatine.manibogo_oms_v2.order.command.domain.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class OrderStateChangedEvent extends Event {

    private final String orderNumber;

    private final String previousStateName;

    private final String newStateName;

    private final LocalDateTime changedAt;

}
