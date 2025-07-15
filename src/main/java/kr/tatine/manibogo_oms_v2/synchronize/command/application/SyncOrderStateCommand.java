package kr.tatine.manibogo_oms_v2.synchronize.command.application;

import java.time.LocalDateTime;

public record SyncOrderStateCommand(
        String orderNumber,
        String targetState,
        LocalDateTime changedAt
) { }
