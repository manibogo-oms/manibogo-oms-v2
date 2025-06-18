package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter @Setter
@NoArgsConstructor
public class ProductRowsForm {

    private List<Row> rows = new ArrayList<>();


    @ToString
    @Getter @Setter
    @NoArgsConstructor
    public static class Row {

        private Boolean isSelected;

        private String productNumber;

        private String productName;

        private Integer priority;

        private Boolean isEnabled;

    }

}
