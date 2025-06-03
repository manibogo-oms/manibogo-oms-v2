package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import kr.tatine.manibogo_oms_v2.common.model.Money;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order.ItemOrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order.ShippingInfo;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.Customer;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.OrderNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.Recipient;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.SalesChannel;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNumber;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SyncExternalItemOrderCommand(
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
        LocalDate dispatchDeadline) { }