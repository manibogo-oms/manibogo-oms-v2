package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.order.command.application.PlaceOrderCommand;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaceOrderForm {

    private String customerName;

    private String customerTel;

    private String customerMemo;

    private String recipientName;

    private String recipientTel1;

    private String recipientTel2;

    private String recipientZipCode;

    private String recipientAddress1;

    private String recipientAddress2;

    private String productNumber;

    private Integer amount = 1;

    private List<PlaceOrderOptionForm> options;

    private ShippingMethod shippingMethod;

    private ChargeType shippingChargeType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchDeadline;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate preferredShipsOn;

    private String purchaseMemo;

    private String shippingMemo;

    private String adminMemo;

    private Long totalPrice;

    private Boolean isRecipientSameAsCustomer;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class PlaceOrderOptionForm {

        private String key;

        private String value;

    }

    public PlaceOrderCommand toCommand() {

        final String recipientName =
                getIsRecipientSameAsCustomer() ? getCustomerName() : getRecipientName();

        final String recipientTel1 =
                getIsRecipientSameAsCustomer() ? getCustomerTel() : getRecipientTel1();

        final String recipientTel2 = getIsRecipientSameAsCustomer() ? null : getRecipientTel2();

        final List<PlaceOrderCommand.PlaceOrderOptionCommand> options = getOptions() == null
                ? List.of()
                : getOptions().stream().map(option -> new PlaceOrderCommand.PlaceOrderOptionCommand(option.getKey(), option.getValue())).toList();

        return new PlaceOrderCommand(
                getCustomerName(),
                getCustomerTel(),
                getCustomerMemo(),
                recipientName,
                recipientTel1,
                recipientTel2,
                getRecipientZipCode(),
                getRecipientAddress1(),
                getRecipientAddress2(),
                getProductNumber(),
                getAmount(),
                options,
                getShippingMethod(),
                getShippingChargeType(),
                getDispatchDeadline(),
                getPreferredShipsOn(),
                getPurchaseMemo(),
                getShippingMemo(),
                getAdminMemo(),
                getTotalPrice());
    }

}
