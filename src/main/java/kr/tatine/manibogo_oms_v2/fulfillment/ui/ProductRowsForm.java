package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.common.model.SelectableRow;
import kr.tatine.manibogo_oms_v2.common.model.SelectableRowsForm;
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

        private String productNumber;

        private String productName;

        private Integer priority;

        private Boolean isEnabled;

    }

}
