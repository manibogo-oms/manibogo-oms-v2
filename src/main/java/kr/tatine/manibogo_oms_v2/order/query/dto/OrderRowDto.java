package kr.tatine.manibogo_oms_v2.order.query.dto;

import kr.tatine.manibogo_oms_v2.order.command.domain.OrderState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderRowDto {

    private boolean isRowSelected;
    private OrderState orderState;
    private String orderNumber;
    private String productName;
    private int shippingBundleCount;
    private int amount;
    private String shippingRegionName;
    private String buyerName;
    private String receiverName;
    private LocalDateTime orderPlacedAt;
    private LocalDateTime dispatchDeadlineAt;
    private LocalDate preferShipsOn;
    private LocalDateTime purchasedAt;
    private LocalDateTime dispatchedAt;
    private LocalDateTime shippedAt;
    private String shippingTrackingNumber;
    private LocalDateTime confirmedAt;
    private LocalDateTime canceledAt;
    private LocalDateTime refundedAt;
    private String buyerMemo;
    private String purchaseMemo;
    private String shippingMemo;
    private String adminMemo;

}
