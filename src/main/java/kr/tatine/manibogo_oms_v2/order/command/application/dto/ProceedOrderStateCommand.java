package kr.tatine.manibogo_oms_v2.order.command.application.dto;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;

public record ProceedOrderStateCommand(
        String orderNumber,
        OrderState targetState
) { }
