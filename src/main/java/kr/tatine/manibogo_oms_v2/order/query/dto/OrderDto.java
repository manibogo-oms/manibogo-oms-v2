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
SELECT DISTINCT
    o.order_number,
    o.sales_channel,
    o.`state` AS 'order_state',
    p.product_number,
    p.`name` AS 'product_name',
    opo_agg.option_key1,
    opo_agg.option_key2,
    opo_agg.option_key3,
    opo_agg.option_label1,
    opo_agg.option_label2,
    opo_agg.option_label3,
    '0' AS 'item_order_bundle_count',
    o.amount,
    r.sido,
    r.sigungu,
    o.customer_name,
    o.customer_phone_number,
    o.recipient_name,
    o.recipient_phone_number_1 as 'recipient_phone_number1',
    o.recipient_phone_number_2 as 'recipient_phone_number2',
    o.address1 as 'recipient_address',
    o.address2 as 'recipient_detail_address',
    o.zip_code as 'recipient_zip_code',
    o.placed_at,
    o.dispatch_deadline,
    o.preferred_shipping_date,
    ioh_agg.purchased_at,
    ioh_agg.dispatched_at,
    ioh_agg.shipped_at,
    o.tracking_number AS 'shipping_tracking_number',
    ioh_agg.confirmed_at,
    ioh_agg.cancelled_at,
    ioh_agg.refunded_at,
    o.purchase_memo,
    o.shipping_memo,
    o.admin_memo,
    o.customer_message,
    o.method AS 'shipping_method',
    o.charge_type AS 'shipping_charge_type',
    o.shipping_bundle_number,
    o.company_name as 'shipping_company',
    o.final_price
FROM
    orders AS o
    JOIN product AS p ON o.product_number = p.product_number
    JOIN region AS r ON o.zip_code = r.zip_code
    LEFT JOIN (
        SELECT
            opo.order_number,
            MAX(CASE WHEN opo.option_seq = 0 THEN v.option_key ELSE NULL END) AS option_key1,
            MAX(CASE WHEN opo.option_seq = 0 THEN v.`label` ELSE NULL END) AS option_label1,
            MAX(CASE WHEN opo.option_seq = 1 THEN v.option_key ELSE NULL END) AS option_key2,
            MAX(CASE WHEN opo.option_seq = 1 THEN v.`label` ELSE NULL END) AS option_label2,
            MAX(CASE WHEN opo.option_seq = 2 THEN v.option_key ELSE NULL END) AS option_key3,
            MAX(CASE WHEN opo.option_seq = 2 THEN v.`label` ELSE NULL END) AS option_label3
        FROM
            order_product_option AS opo
            JOIN orders o ON o.order_number = opo.order_number
            JOIN variant v ON v.product_number = o.product_number
            AND v.option_key = opo.option_key
            AND v.option_value = opo.option_value
        GROUP BY
            opo.order_number
    ) AS opo_agg ON o.order_number = opo_agg.order_number
     -- 상품주문 상태 이력 집계 View
    LEFT JOIN (
        SELECT
            ioh.order_number,
            MAX(CASE WHEN ioh.new_state = 'PURCHASED' THEN ioh.changed_at ELSE NULL END) AS 'purchased_at',
            MAX(CASE WHEN ioh.new_state = 'DISPATCHED' THEN ioh.changed_at ELSE NULL END) AS 'dispatched_at',
            MAX(CASE WHEN ioh.new_state = 'SHIPPED' THEN ioh.changed_at ELSE NULL END) AS 'shipped_at',
            MAX(CASE WHEN ioh.new_state = 'CONFIRMED' THEN ioh.changed_at ELSE NULL END) AS 'confirmed_at',
            MAX(CASE WHEN ioh.new_state = 'CANCELLED' THEN ioh.changed_at ELSE NULL END) AS 'cancelled_at',
            MAX(CASE WHEN ioh.new_state = 'REFUNDED' THEN ioh.changed_at ELSE NULL END) AS 'refunded_at'
        FROM
            order_log ioh
        GROUP BY
            ioh.order_number
    ) AS ioh_agg ON o.order_number = ioh_agg.order_number
WHERE p.is_enabled = true
GROUP BY o.order_number
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

    private int itemOrderBundleCount;

    private int amount;

    private String sido;

    private String sigungu;

    private String customerName;

    private String customerPhoneNumber;

    private String recipientName;

    private String recipientPhoneNumber1;

    private String recipientPhoneNumber2;

    private String recipientAddress;

    private String recipientDetailAddress;

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

    private String shippingCompany;

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
