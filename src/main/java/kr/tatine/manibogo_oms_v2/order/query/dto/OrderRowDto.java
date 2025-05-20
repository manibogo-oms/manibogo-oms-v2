package kr.tatine.manibogo_oms_v2.order.query.dto;

import kr.tatine.manibogo_oms_v2.order.command.domain.OrderState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDate;

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
    private LocalDate orderPlacedOn;
    private LocalDate dispatchDeadlineOn;
    private LocalDate preferShipsOn;
    private LocalDate purchasedOn;
    private LocalDate dispatchedOn;
    private LocalDate shippedOn;
    private String shippingTrackingNumber;
    private LocalDate confirmedOn;
    private LocalDate canceledOn;
    private LocalDate refundedOn;
    private String buyerMemo;
    private String purchaseMemo;
    private String shippingMemo;
    private String adminMemo;

}
