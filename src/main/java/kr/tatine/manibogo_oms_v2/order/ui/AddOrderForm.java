package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddOrderForm {

    private String customerName;

    private String customerTel;

    private String customerMemo;

    private String recipientName;

    private String recipientTel1;

    private String recipientTel2;

    private String recipientZipCode;

    private String recipientAddress1;

    private String recipientAddress2;

    private ShippingMethod shippingMethod;

    private ChargeType shippingChargeType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchDeadline;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate preferredShipsOn;

    private String shippingMemo;

    private List<AddItemOrderForm> itemOrderForms = new ArrayList<>();

}
