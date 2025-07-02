package kr.tatine.manibogo_oms_v2.order.command.application;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;

import java.time.LocalDate;
import java.util.List;

public record PlaceOrderCommand(
        String customerName,
        String customerTel,
        String customerMessage,
        String recipientName,
        String recipientTel1,
        String recipientTel2,
        String recipientZipCode,
        String recipientAddress1,
        String recipientAddress2,
        String productNumber,
        Integer amount,
        List<PlaceOrderOptionCommand> options,
        ShippingMethod shippingMethod,
        ChargeType shippingChargeType,
        LocalDate dispatchDeadline,
        LocalDate preferredShippingDate,
        String purchaseMemo,
        String shippingMemo,
        String adminMemo,
        Long totalPrice
) {

    public record PlaceOrderOptionCommand(
            String key,
            String value
    ) {}

}