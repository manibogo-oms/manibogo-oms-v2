package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.common.model.SelectableRow;
import kr.tatine.manibogo_oms_v2.common.model.SelectableRowsForm;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.EditItemOrderSummaryCommand;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.ProceedItemOrderStateCommand;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
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
public class ItemOrderRowsForm implements SelectableRowsForm<ItemOrderRowsForm.Row> {

    private List<Row> rows = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Row implements SelectableRow {

        private Boolean isSelected;

        private String itemOrderNumber;

        private ItemOrderState itemOrderState;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dispatchDeadline;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate preferredShipsOn;

        private String purchaseMemo;

        private String shippingMemo;

        private String adminMemo;


        public EditItemOrderSummaryCommand toEditSummaryCommand() {
            return new EditItemOrderSummaryCommand(
                getItemOrderNumber(),
                getItemOrderState(),
                getDispatchDeadline(),
                getPreferredShipsOn(),
                getPurchaseMemo(),
                getShippingMemo(),
                getAdminMemo()
            );
        }

        public ProceedItemOrderStateCommand toProceedStateCommand(ItemOrderState targetState) {
            return new ProceedItemOrderStateCommand(
                    getItemOrderNumber(), targetState);
        }

    }

}
