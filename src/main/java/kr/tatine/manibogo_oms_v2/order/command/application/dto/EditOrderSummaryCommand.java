package kr.tatine.manibogo_oms_v2.order.command.application.dto;

import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;

import java.time.LocalDate;

public record EditOrderSummaryCommand(
        String orderNumber,
        OrderState orderState,
        LocalDate dispatchDeadline,
        LocalDate preferredShippingDate,
        ShippingMethod shippingMethod,
        ChargeType shippingChargeType,
        String trackingNumber,
        String parcelCompany,
        String purchaseMemo,
        String shippingMemo,
        String adminMemo
) { }
