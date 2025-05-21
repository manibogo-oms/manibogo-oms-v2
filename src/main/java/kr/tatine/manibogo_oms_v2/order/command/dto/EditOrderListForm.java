package kr.tatine.manibogo_oms_v2.order.command.dto;

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
