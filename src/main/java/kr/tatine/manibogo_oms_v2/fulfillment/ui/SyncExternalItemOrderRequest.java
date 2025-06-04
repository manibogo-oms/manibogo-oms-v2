package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.SyncExternalItemOrderCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SyncExternalItemOrderRequest(
        String orderNumber,
        String customerName,
        String customerPhoneNumber,
        String salesChannel,
        String recipientName,
        String recipientPhoneNumber1,
        String recipientPhoneNumber2,
        String recipientAddress1,
        String recipientAddress2,
        String recipientZipcode,
        String itemOrderNumber,
        String productNumber,
        String productName,
        String productOption,
        Integer amount,
        Money totalPrice,
        String shippingMethod,
        String shippingChargeType,
        LocalDateTime itemOrderPlacedAt,
        LocalDate dispatchDeadline) {

    public SyncExternalItemOrderCommand toCommand() {
        return new SyncExternalItemOrderCommand(
                orderNumber(),
                customerName(),
                customerPhoneNumber(),
                salesChannel(),
                recipientName(),
                recipientPhoneNumber1(),
                recipientPhoneNumber2(),
                recipientAddress1(),
                recipientAddress2(),
                recipientZipcode(),
                itemOrderNumber(),
                productNumber(),
                productName(),
                productOption(),
                amount(),
                totalPrice(),
                shippingMethod(),
                shippingChargeType(),
                itemOrderPlacedAt(),
                dispatchDeadline());
    }

}