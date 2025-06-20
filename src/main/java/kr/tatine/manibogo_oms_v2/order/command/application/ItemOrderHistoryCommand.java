package kr.tatine.manibogo_oms_v2.order.command.application;

import java.time.LocalDateTime;

public record ItemOrderHistoryCommand(
        String itemOrderNumber,
        String previousState,
        String newState,
        LocalDateTime itemOrderPlacedAt
) { }
