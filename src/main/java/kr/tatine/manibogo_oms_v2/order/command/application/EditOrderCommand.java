package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;

import java.time.LocalDate;

public record EditOrderCommand(
        String orderNumber,
        OrderState state,
        LocalDate dispatchDeadline,
        LocalDate preferredShippingDate,
        ShippingMethod shippingMethod,
        ChargeType shppingChargeType,
        String trackingNumber,
        String parcelCompany,
        String purchaseMemo,
        String shippingMemo,
        String adminMemo
) { }
