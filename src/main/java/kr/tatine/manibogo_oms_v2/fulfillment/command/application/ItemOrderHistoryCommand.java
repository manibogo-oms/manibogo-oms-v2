package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import java.time.LocalDateTime;

public record ItemOrderHistoryCommand(
        String itemOrderNumber,
        String previousState,
        String newState,
        LocalDateTime itemOrderPlacedAt
) { }
