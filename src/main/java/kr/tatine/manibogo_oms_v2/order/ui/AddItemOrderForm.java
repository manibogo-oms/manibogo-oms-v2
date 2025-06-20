package kr.tatine.manibogo_oms_v2.order.ui;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddItemOrderForm {

    private String productNumber;

    private String productName;

    private Integer amount;

    private List<AddItemOrderOptionForm> options;

    private String purchaseMemo;

    private String shippingMemo;

    private String adminMemo;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class AddItemOrderOptionForm {

        private String key;

        private String value;

    }

}
