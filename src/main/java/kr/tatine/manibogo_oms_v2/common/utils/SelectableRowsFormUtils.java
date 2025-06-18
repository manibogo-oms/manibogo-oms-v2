package kr.tatine.manibogo_oms_v2.common.utils;

import kr.tatine.manibogo_oms_v2.common.model.ErrorLevel;
import kr.tatine.manibogo_oms_v2.common.model.ErrorResult;
import kr.tatine.manibogo_oms_v2.common.model.SelectableRow;
import kr.tatine.manibogo_oms_v2.common.model.SelectableRowsForm;

import java.util.function.BiConsumer;

public class SelectableRowsFormUtils {

    private SelectableRowsFormUtils() {}


    public static <T extends SelectableRow> void handle(
            SelectableRowsForm<T> selectableRowsForm,
            ErrorResult errorResult,
            BiConsumer<Integer, T> process
    ) {

        int selectedRowCount = 0;

        for (int i = 0; i < selectableRowsForm.getRows().size(); i ++) {
            final T row = selectableRowsForm.getRows().get(i);

            if (!row.getIsSelected()) continue;

            selectedRowCount ++;

            process.accept(i, row);
        }

        if (selectedRowCount == 0) {
            errorResult.reject(ErrorLevel.WARN,"requireSelect");
        }
    }

    public static String getRowsFieldName(Integer index, String fieldName) {
            return "%s[%d].%s".formatted("rows", index, fieldName);
    }

}
