package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import kr.tatine.manibogo_oms_v2.fulfillment.ui.FulfillmentForm;
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
    f.id,
    io.item_order_number,
    o.order_number,
    o.sales_channel,
    f.`state` AS 'item_order_state',
    p.product_number,
    p.`name` AS 'product_name',
    '개발필요' as 'option_info',
    item_order_cnt.item_order_count AS 'item_order_bundle_count',
    io.amount,
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
    io.created_at as 'placed_at',
    f.dispatch_deadline,
    f.preferred_shipping_date as `preferred_ships_on`,
    ioh_agg.purchased_at,
    ioh_agg.dispatched_at,
    ioh_agg.shipped_at,
    f.tracking_number AS 'shipping_tracking_number',
    ioh_agg.confirmed_at,
    ioh_agg.cancelled_at,
    ioh_agg.refunded_at,
    '' AS 'customer_memo',
    f.purchase_memo,
    f.shipping_memo,
    f.admin_memo,
    o.customer_message,
    io.shipping_method,
    io.shipping_charge_type,
    f.company_name as 'shipping_company'
FROM
    fulfillment AS f
    JOIN item_order AS io ON f.item_order_number = io.item_order_number
    JOIN orders AS o ON io.order_number = o.order_number
    JOIN product AS p ON io.product_number = p.product_number
    JOIN region AS r ON o.zip_code = r.zip_code
    -- 주문별 아이템 개수 집계 View
    LEFT JOIN (
        SELECT
            item_order.order_number,
            COUNT(item_order.item_order_number) AS item_order_count
        FROM
            item_order
        GROUP BY
            item_order.order_number
    ) AS item_order_cnt ON o.order_number = item_order_cnt.order_number
     -- 상품주문 상태 이력 집계 View
    LEFT JOIN (
        SELECT
            ioh.fulfillment_id,
            MAX(CASE WHEN ioh.new_state = 'PURCHASED' THEN ioh.changed_at ELSE NULL END) AS 'purchased_at',
            MAX(CASE WHEN ioh.new_state = 'DISPATCHED' THEN ioh.changed_at ELSE NULL END) AS 'dispatched_at',
            MAX(CASE WHEN ioh.new_state = 'SHIPPED' THEN ioh.changed_at ELSE NULL END) AS 'shipped_at',
            MAX(CASE WHEN ioh.new_state = 'CONFIRMED' THEN ioh.changed_at ELSE NULL END) AS 'confirmed_at',
            MAX(CASE WHEN ioh.new_state = 'CANCELLED' THEN ioh.changed_at ELSE NULL END) AS 'cancelled_at',
            MAX(CASE WHEN ioh.new_state = 'REFUNDED' THEN ioh.changed_at ELSE NULL END) AS 'refunded_at'
        FROM
            fulfillment_log ioh
        GROUP BY
            ioh.fulfillment_id
    ) AS ioh_agg ON f.id = ioh_agg.fulfillment_id
WHERE p.is_enabled = true
""")
public class FulfillmentDto {

    @Id
    private Long id;

    private String itemOrderNumber;

    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private SalesChannel salesChannel;

    @Enumerated(EnumType.STRING)
    private OrderState itemOrderState;

    private String productNumber;

    private String productName;

    private String optionInfo;

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
    private LocalDate preferredShipsOn;

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

    private String shippingCompany;


    public FulfillmentForm.Row toEditFormRow() {
        final FulfillmentForm.Row row = new FulfillmentForm.Row();

        row.setIsSelected(false);
        row.setId(getId());
        row.setItemOrderNumber(getItemOrderNumber());
        row.setState(getItemOrderState());
        row.setDispatchDeadline(getDispatchDeadline());
        row.setPreferredShippingDate(getPreferredShipsOn());
        row.setPurchaseMemo(getPurchaseMemo());
        row.setShippingMemo(getShippingMemo());
        row.setAdminMemo(getAdminMemo());

        return row;
    }

}
