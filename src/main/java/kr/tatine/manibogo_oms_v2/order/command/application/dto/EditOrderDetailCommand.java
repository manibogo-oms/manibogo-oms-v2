package kr.tatine.manibogo_oms_v2.order.command.application.dto;

import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;

import java.time.LocalDate;

public record EditOrderDetailCommand(
        String orderNumber,

        OrderState orderState,

        String purchaseMemo,

        String customerName,

        String customerTel,

        String customerMessage,

        String recipientName,

        String recipientTel1,

        String recipientTel2,

        String recipientZipCode,

        String recipientAddress1,

        String recipientAddress2,

        ShippingMethod shippingMethod,

        ChargeType shippingChargeType,

        String shippingBundleNumber,

        LocalDate dispatchDeadline,

        LocalDate preferredShippingDate,

        String shippingMemo,

        String adminMemo
) { }
