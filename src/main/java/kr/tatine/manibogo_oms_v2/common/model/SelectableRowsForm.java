package kr.tatine.manibogo_oms_v2.common.model;

import java.util.List;

public interface SelectableRowsForm<T extends SelectableRow> {

    List<T> getRows();

}
