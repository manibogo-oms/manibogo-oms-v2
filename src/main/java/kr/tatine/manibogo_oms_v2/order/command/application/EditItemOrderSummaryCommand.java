package kr.tatine.manibogo_oms_v2.order.command.application;

import jakarta.validation.constraints.NotNull;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState;

import java.time.LocalDate;

public record EditItemOrderSummaryCommand(
        @NotNull String itemOrderNumber,

        ItemOrderState itemOrderState,

        @NotNull
        LocalDate dispatchDeadline,

        LocalDate preferredShipsOn,

        String purchaseMemo,

        String shippingMemo,

        String adminMemo
) { }
