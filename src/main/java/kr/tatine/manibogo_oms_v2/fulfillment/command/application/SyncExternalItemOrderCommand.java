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
        OrderNumber orderNumber,
        Customer customer,
        SalesChannel salesChannel,
        Recipient recipient,
        ItemOrderNumber itemOrderNumber,
        ProductNumber productNumber,
        String productName,
        String productOption,
        Integer amount,
        Money totalPrice,
        ShippingInfo shippingInfo,
        LocalDateTime itemOrderPlacedAt,
        LocalDate dispatchDeadline) { }