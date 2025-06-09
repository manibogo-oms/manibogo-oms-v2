package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.SalesChannel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
public class FulfillmentDto {

    private String itemOrderNumber;

    private String orderNumber;

    private SalesChannel salesChannel;

    private ItemOrderState itemOrderState;

    private String productName;

    private int shippingBundleCount = 1;

    private int amount;

    private String shippingRegionName = "아직";

    private String customerName;

    private String recipientName;

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
