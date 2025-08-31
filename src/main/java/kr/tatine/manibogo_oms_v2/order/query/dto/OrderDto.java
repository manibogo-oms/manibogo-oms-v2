package kr.tatine.manibogo_oms_v2.order.query.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.order.ui.EditOrderSummaryForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class OrderDto {

    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private SalesChannel salesChannel;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    private String productNumber;

    private String productName;

    private String optionKey1;

    private String optionLabel1;

    private String optionKey2;

    private String optionLabel2;

    private String optionKey3;

    private String optionLabel3;

    private int shippingBundleCount;

    private int amount;

    private String sido;

    private String sigungu;

    private String customerName;

    private String customerTel;

    private String recipientName;

    private String recipientTel1;

    private String recipientTel2;

    private String recipientAddr1;

    private String recipientAddr2;

    private String recipientZipCode;

    private LocalDateTime placedAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchDeadline;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate preferredShippingDate;

    private LocalDateTime purchasedAt;

    private LocalDateTime dispatchedAt;

    private LocalDateTime shippedAt;

    private String shippingTrackingNumber;

    private LocalDateTime confirmedAt;

    private LocalDateTime cancelledAt;

    private LocalDateTime refundedAt;

    private String customerMessage;

    private String purchaseMemo;

    private String shippingMemo;

    private String adminMemo;

    @Enumerated(EnumType.STRING)
    private ShippingMethod shippingMethod;

    @Enumerated(EnumType.STRING)
    private ChargeType shippingChargeType;

    private String shippingBundleNumber;

    private String shippingCompanyName;

    private Long finalPrice;

    public EditOrderSummaryForm.Row toEditFormRow() {
        final EditOrderSummaryForm.Row row = new EditOrderSummaryForm.Row();

        row.setIsSelected(false);
        row.setOrderNumber(getOrderNumber());
        row.setState(getOrderState());
        row.setDispatchDeadline(getDispatchDeadline());
        row.setPreferredShippingDate(getPreferredShippingDate());
        row.setPurchaseMemo(getPurchaseMemo());
        row.setShippingMemo(getShippingMemo());
        row.setAdminMemo(getAdminMemo());

        return row;
    }

}
