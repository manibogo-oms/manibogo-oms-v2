package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import kr.tatine.manibogo_oms_v2.common.validator.DescribableEnum;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;

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
