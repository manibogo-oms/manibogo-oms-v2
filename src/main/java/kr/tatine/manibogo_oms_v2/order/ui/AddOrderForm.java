package kr.tatine.manibogo_oms_v2.order.ui;

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
public class AddOrderForm {

    private List<AddItemOrderForm> itemOrderForms = new ArrayList<>();

}
