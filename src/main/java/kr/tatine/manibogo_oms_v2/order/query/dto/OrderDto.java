package kr.tatine.manibogo_oms_v2.order.query.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import kr.tatine.manibogo_oms_v2.order.ui.EditOrderSummaryForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Subselect;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Subselect("""
SELECT
    o.order_number,
    o.sales_channel,
    o.order_state,
    p.product_number,
    p.product_name,
    o.option_key1,
    o.option_key2,
    o.option_key3,
    o.option_value1 as 'option_label1',
    o.option_value2 as 'option_label2',
    o.option_value3 as 'option_label3',
    '1' as 'shipping_bundle_count',
    o.amount,
    r.sido,
    r.sigungu,
    o.customer_name,
    o.customer_tel,
    o.recipient_name,
    o.recipient_tel1,
    o.recipient_tel2,
    o.recipient_addr1,
    o.recipient_addr2,
    o.recipient_zip_code,
    ish.placed_at,
    o.dispatch_deadline,
    o.preferred_shipping_date,
    ish.purchased_at,
    ish.dispatched_at,
    ish.shipped_at,
    o.shipping_tracking_number,
    ish.confirmed_at,
    ish.cancelled_at,
    ish.refunded_at,
    o.purchase_memo,
    o.shipping_memo,
    o.admin_memo,
    o.customer_message,
    o.shipping_method,
    o.shipping_charge_type,
    o.shipping_bundle_number,
    o.shipping_company_name,
    o.final_price
FROM
    orders AS o
    LEFT JOIN product AS p ON o.product_number = p.product_number
    LEFT JOIN zip_code_region AS r ON o.recipient_zip_code = r.zip_code
    LEFT JOIN order_state_history AS ish ON o.order_number = ish.order_number
WHERE p.is_enabled = true
""")
public class OrderDto {

    @Id
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
