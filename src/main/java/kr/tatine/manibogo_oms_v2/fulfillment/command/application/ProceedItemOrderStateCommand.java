package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;

public record ProceedItemOrderStateCommand(
        String itemOrderNumber,
        ItemOrderState targetState
) { }
