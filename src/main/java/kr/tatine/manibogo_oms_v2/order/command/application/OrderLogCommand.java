package kr.tatine.manibogo_oms_v2.order.command.application;

import java.time.LocalDateTime;

public record OrderLogCommand(
        String orderNumber,
        String previousState,
        String newState,
        LocalDateTime changedAt
) { }
