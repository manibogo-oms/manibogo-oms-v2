package kr.tatine.manibogo_oms_v2.synchronize.ui;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.tatine.manibogo_oms_v2.common.validator.DescribableEnum;
import kr.tatine.manibogo_oms_v2.common.validator.OptionInfo;
import kr.tatine.manibogo_oms_v2.common.validator.PhoneNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record ExternalItemOrderRequest (
        @NotBlank(message = "{notBlank.externalOrder.orderNumber}")
        String orderNumber,

        @NotBlank(message = "{notBlank.externalOrder.customerName}")
        String customerName,

        @PhoneNumber(message = "{phoneNumber.externalOrder.customerPhoneNumber}")
        String customerPhoneNumber,

        String customerMessage,

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
        LocalDateTime orderPlacedAt,

        @NotNull(message = "{notNull.externalOrder.dispatchDeadline}")
        LocalDate dispatchDeadline
) {

}