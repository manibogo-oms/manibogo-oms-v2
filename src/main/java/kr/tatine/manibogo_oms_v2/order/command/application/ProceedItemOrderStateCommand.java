package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState;

public record ProceedItemOrderStateCommand(
        String itemOrderNumber,
        ItemOrderState targetState
) { }
