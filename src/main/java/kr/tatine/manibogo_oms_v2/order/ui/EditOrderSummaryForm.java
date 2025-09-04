package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.common.ui.SelectableRow;
import kr.tatine.manibogo_oms_v2.common.ui.SelectableRowsForm;
import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditOrderSummaryForm implements SelectableRowsForm<EditOrderSummaryForm.Row> {

    private List<Row> rows = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Row implements SelectableRow {

        private Boolean isSelected;

        private String orderNumber;

        private OrderState state;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dispatchDeadline;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate preferredShippingDate;

        private ShippingMethod shippingMethod;

        private ChargeType chargeType;

        private String trackingNumber;

        private String parcelCompany;

        private String purchaseMemo;

        private String shippingMemo;

        private String adminMemo;

    }

}
