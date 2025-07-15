package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.order.command.application.dto.EditOrderDetailCommand;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EditOrderForm {

    private String orderNumber;

    private SalesChannel salesChannel;

    private String productName;

    private Integer amount;

    private Long finalPrice;

    private OrderState orderState;

    private String optionKey1;

    private String optionLabel1;

    private String optionKey2;

    private String optionLabel2;

    private String optionKey3;

    private String optionLabel3;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime placedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime purchasedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dispatchedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime shippedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime confirmedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime cancelledAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime refundedAt;

    private String purchaseMemo;

    private String customerName;

    private String customerTel;

    private String customerMessage;

    private String recipientName;

    private String recipientTel1;

    private String recipientTel2;

    private String recipientZipCode;

    private String recipientAddress1;

    private String recipientAddress2;

    private ShippingMethod shippingMethod;

    private ChargeType shippingChargeType;

    private String shippingBundleNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchDeadline;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate preferredShippingDate;

    private String shippingMemo;

    private String adminMemo;

    public static EditOrderForm of(OrderDto orderDto) {
        final EditOrderForm form = new EditOrderForm();

        form.setOrderNumber(orderDto.getOrderNumber());
        form.setSalesChannel(orderDto.getSalesChannel());

        form.setProductName(orderDto.getProductName());

        form.setOptionKey1(orderDto.getOptionKey1());
        form.setOptionLabel1(orderDto.getOptionLabel1());

        form.setOptionKey2(orderDto.getOptionKey2());
        form.setOptionLabel2(orderDto.getOptionLabel2());

        form.setOptionKey3(orderDto.getOptionKey3());
        form.setOptionLabel3(orderDto.getOptionLabel3());

        form.setAmount(orderDto.getAmount());
        form.setFinalPrice(orderDto.getFinalPrice());
        form.setPurchaseMemo(orderDto.getPurchaseMemo());

        form.setOrderState(orderDto.getOrderState());
        form.setPlacedAt(orderDto.getPlacedAt());
        form.setPurchasedAt(orderDto.getPurchasedAt());
        form.setDispatchedAt(orderDto.getDispatchedAt());
        form.setShippedAt(orderDto.getShippedAt());
        form.setConfirmedAt(orderDto.getConfirmedAt());
        form.setCancelledAt(orderDto.getCancelledAt());
        form.setRefundedAt(orderDto.getRefundedAt());

        form.setCustomerName(orderDto.getCustomerName());
        form.setCustomerTel(orderDto.getCustomerPhoneNumber());
        form.setCustomerMessage(orderDto.getCustomerMessage());

        form.setRecipientName(orderDto.getRecipientName());
        form.setRecipientTel1(orderDto.getRecipientPhoneNumber1());
        form.setRecipientTel2(orderDto.getRecipientPhoneNumber2());
        form.setRecipientZipCode(orderDto.getRecipientZipCode());
        form.setRecipientAddress1(orderDto.getRecipientAddress());
        form.setRecipientAddress2(orderDto.getRecipientDetailAddress());

        form.setShippingMethod(orderDto.getShippingMethod());
        form.setShippingChargeType(orderDto.getShippingChargeType());
        form.setDispatchDeadline(orderDto.getDispatchDeadline());
        form.setPreferredShippingDate(orderDto.getPreferredShippingDate());
        form.setShippingMemo(orderDto.getShippingMemo());
        form.setShippingBundleNumber(orderDto.getShippingBundleNumber());

        form.setAdminMemo(orderDto.getAdminMemo());

        return form;
    }

    public EditOrderDetailCommand toCommand() {
        return new EditOrderDetailCommand(
                getOrderNumber(),
                getOrderState(),
                getPurchaseMemo(),
                getCustomerName(),
                getCustomerTel(),
                getCustomerMessage(),
                getRecipientName(),
                getRecipientTel1(),
                getRecipientTel2(),
                getRecipientZipCode(),
                getRecipientAddress1(),
                getRecipientAddress2(),
                getShippingMethod(),
                getShippingChargeType(),
                getShippingBundleNumber(),
                getDispatchDeadline(),
                getPreferredShippingDate(),
                getShippingMemo(),
                getAdminMemo()
        );
    }

}
