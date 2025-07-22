package kr.tatine.manibogo_oms_v2.logistics.query.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Subselect;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Subselect("""
SELECT
	o.shipping_bundle_number,
	SUM(CASE WHEN o.`order_state` = 'PURCHASED' THEN 1 ELSE 0 END) AS 'purchased_count',
	SUM(CASE WHEN o.`order_state` = 'DISPATCHED' THEN 1 ELSE 0 END) AS 'dispatched_count',
	SUM(CASE WHEN o.`order_state` = 'SHIPPED' THEN 1 ELSE 0 END) AS 'shipped_count',
	SUM(CASE WHEN o.`order_state` = 'CANCELLED' THEN 1 ELSE 0 END) AS 'cancelled_count',
	SUM(CASE WHEN o.`order_state` = 'REFUNDED' THEN 1 ELSE 0 END) AS 'refunded_count',
	MAX(o.placed_at) as 'recently_placed_at',
	o.recipient_addr1,
	r.sido,
	r.sigungu,
	o.customer_name,
	o.customer_tel,
	o.recipient_name,
	o.recipient_tel1,
	o.recipient_tel2,
	o.customer_message,
	o.shipping_memo
FROM
	orders AS o
	LEFT JOIN zip_code_region AS r ON o.recipient_zip_code = r.zip_code
WHERE
	o.`shipping_method` = 'DIRECT'
	AND o.`order_state` IN ('PURCHASED', 'DISPATCHED', 'SHIPPED', 'CANCELLED', 'REFUNDED')
GROUP BY
	o.shipping_bundle_number
""")
public class LogisticsDto {

    @Id
    private String shippingBundleNumber;

    private int purchasedCount;

    private Integer dispatchedCount;

    private Integer shippedCount;

    private Integer cancelledCount;

    private Integer refundedCount;

    private LocalDateTime recentlyPlacedAt;

    private String recipientAddr1;

    private String sido;

    private String sigungu;

    private String customerName;

    private String customerTel;

    private String recipientName;

    private String recipientTel1;

    private String recipientTel2;

    private String customerMessage;

    private String shippingMemo;

}
