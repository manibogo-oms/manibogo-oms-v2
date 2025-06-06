package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.tatine.manibogo_oms_v2.common.validator.DescribableEnum;
import kr.tatine.manibogo_oms_v2.common.validator.OptionInfo;
import kr.tatine.manibogo_oms_v2.common.validator.PhoneNumber;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order.ChargeType;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order.ShippingMethod;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.SalesChannel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public record ExternalItemOrderRequest (
        @NotBlank(message = "{notBlank.externalOrder.orderNumber}")
        String orderNumber,

        @NotBlank(message = "{notBlank.externalOrder.customerName}")
        String customerName,

        @PhoneNumber(message = "{phoneNumber.externalOrder.customerPhoneNumber}")
        String customerPhoneNumber,

        @DescribableEnum(
                message = "{describableEnum.externalOrder.salesChannel}",
                enumClazz = SalesChannel.class)
        String salesChannel,

        @NotBlank(message = "{notBlank.externalOrder.recipientName}")
        String recipientName,

        @PhoneNumber(message = "{phoneNumber.externalOrder.recipientPhoneNumber1}")
        String recipientPhoneNumber1,

        @PhoneNumber(message = "{phoneNumber.externalOrder.recipientPhoneNumber2}", nullable = true)
        String recipientPhoneNumber2,

        @NotBlank(message = "{notBlank.externalOrder.recipientAddress1}")
        String recipientAddress1,

        String recipientAddress2,

        @NotBlank(message = "{notBlank.externalOrder.recipientAddressZipcode}")
        String recipientAddressZipcode,

        @NotBlank(message = "{notBlank.externalOrder.itemOrderNumber}")
        String itemOrderNumber,

        @NotBlank(message = "{notBlank.externalOrder.productNumber}")
        String productNumber,

        @NotBlank(message = "{notBlank.externalOrder.productName}")
        String productName,

        @OptionInfo
        String optionInfo,

        @NotNull(message = "{notNull.externalOrder.amount}")
        @Min(value = 1, message = "{min.externalOrder.amount}")
        Integer amount,

        @NotNull(message = "{notNull.externalOrder.totalPrice}")
        @Min(value = 1, message = "{min.externalOrder.totalPrice}")
        Long totalPrice,

        @DescribableEnum(
                message = "{describableEnum.externalOrder.shippingMethod}",
                enumClazz = ShippingMethod.class)
        String shippingMethod,

        @DescribableEnum(
                message = "{describableEnum.externalOrder.shippingChargeType}",
                enumClazz = ChargeType.class)
        String shippingChargeType,

        @NotNull(message = "{notNull.externalOrder.itemOrderPlacedAt}")
        LocalDateTime itemOrderPlacedAt,

        @NotNull(message = "{notNull.externalOrder.dispatchDeadline}")
        LocalDate dispatchDeadline
) {

}