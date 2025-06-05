package kr.tatine.manibogo_oms_v2.fulfillment.command.application;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.tatine.manibogo_oms_v2.common.validator.PhoneNumber;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ExternalItemOrderRequest (
        @NotBlank
        String orderNumber,

        @NotBlank
        String customerName,

        @NotBlank @PhoneNumber
        String customerPhoneNumber,

        @NotBlank
        String salesChannel,

        @NotBlank
        String recipientName,

        @NotBlank @PhoneNumber
        String recipientPhoneNumber1,

        @PhoneNumber(nullable = true)
        String recipientPhoneNumber2,

        @NotBlank
        String recipientAddress1,

        String recipientAddress2,

        @NotBlank
        String recipientAddressZipcode,

        @NotBlank
        String itemOrderNumber,

        @NotBlank
        String productNumber,

        @NotBlank
        String productName,

        @NotNull
        List<ExternalItemOrderOptionRequest> options,

        @NotNull @Min(1)
        Integer amount,

        @NotNull @Min(1)
        Long totalPrice,

        @NotBlank
        String shippingMethod,

        @NotBlank
        String shippingChargeType,

        @NotNull
        LocalDateTime itemOrderPlacedAt,

        @NotNull
        LocalDate dispatchDeadline
) {

    public record ExternalItemOrderOptionRequest(
            @NotBlank String key,
            @NotBlank String value
    ) { }

}