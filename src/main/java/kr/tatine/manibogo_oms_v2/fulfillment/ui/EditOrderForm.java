package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EditOrderForm {

    private boolean isRowSelected;

    private String orderNumber;

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

    private String purchaseMemo;

    private String shippingMemo;

    private String adminMemo;

}
