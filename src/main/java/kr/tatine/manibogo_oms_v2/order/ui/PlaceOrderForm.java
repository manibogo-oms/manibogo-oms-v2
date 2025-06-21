package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlaceOrderForm {

    private String customerName;

    private String customerTel;

    private String customerMemo;

    private String recipientName;

    private String recipientTel1;

    private String recipientTel2;

    private String recipientZipCode;

    private String recipientAddress1;

    private String recipientAddress2;

    private String productNumber;

    private Integer amount;

    private List<PlaceOrderOptionForm> options;

    private String purchaseMemo;

    private ShippingMethod shippingMethod;

    private ChargeType shippingChargeType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchDeadline;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate preferredShipsOn;

    private String shippingMemo;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class PlaceOrderOptionForm {

        private String key;

        private String value;

    }

}
