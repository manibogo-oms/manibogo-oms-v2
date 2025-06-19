package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.common.model.SelectableRow;
import kr.tatine.manibogo_oms_v2.common.model.SelectableRowsForm;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.EditVariantCommand;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.VariantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VariantRowsForm implements SelectableRowsForm<VariantRowsForm.Row> {

    private List<Row> rows = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class Row implements SelectableRow {

        private Boolean isSelected;

        private String productNumber;

        private String key;

        private String value;

        private String label;

        public static Row fromDto(VariantDto variant) {
            final Row row = new Row();

            row.setIsSelected(false);
            row.setProductNumber(variant.getProductNumber());
            row.setKey(variant.getKey());
            row.setValue(variant.getValue());
            row.setLabel(variant.getLabel());

            return row;
        }

        public EditVariantCommand toEditCommand() {
            return new EditVariantCommand(productNumber, key, value, label);
        }

    }

}
