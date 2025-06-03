package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.OrderLocation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class OrderDto {

    private boolean isRowSelected;

    private OrderLocation orderLocation;

    private OrderState orderState;

    private String orderNumber;

    private String productName;

    private int shippingBundleCount;

    private int amount;

    private String shippingRegionName;

    private String buyerName;

    private String receiverName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderPlacedOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchDeadlineOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate preferShipsOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchasedOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchedOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shippedOn;

    private String shippingTrackingNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate confirmedOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate canceledOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate refundedOn;

    private String buyerMemo;

    private String purchaseMemo;

    private String shippingMemo;

    private String adminMemo;

}
