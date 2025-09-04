package kr.tatine.manibogo_oms_v2.product.ui;

import kr.tatine.manibogo_oms_v2.common.ui.SelectableRow;
import kr.tatine.manibogo_oms_v2.common.ui.SelectableRowsForm;
import kr.tatine.manibogo_oms_v2.product.command.application.EditProductCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter @Setter
@NoArgsConstructor
public class ProductRowsForm implements SelectableRowsForm<ProductRowsForm.Row> {

    private List<Row> rows = new ArrayList<>();


    @ToString
    @Getter @Setter
    @NoArgsConstructor
    public static class Row implements SelectableRow {

        private Boolean isSelected;

        private String number;

        private String name;

        private Integer priority;

        private Boolean isEnabled;

        public EditProductCommand toEditCommand() {
            return new EditProductCommand(
                    number, name, priority, isEnabled);
        }

    }

}
