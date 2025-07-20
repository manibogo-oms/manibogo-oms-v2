package kr.tatine.manibogo_oms_v2.order.command.application.dto;

import java.time.LocalDateTime;

public record CreateOrderLogCommand(
        String orderNumber,
        String previousState,
        String newState,
        LocalDateTime changedAt,
        String changedBy
) { }
