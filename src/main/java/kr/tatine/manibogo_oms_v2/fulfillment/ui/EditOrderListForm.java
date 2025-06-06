package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EditOrderListForm {

    private List<EditOrderForm> orders;

}
