package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOrderNote {

    private String purchaseMemo;

    private String shippingMemo;

    private String adminMemo;

    public ItemOrderNote(String purchaseMemo, String shippingMemo, String adminMemo) {
        this.purchaseMemo = purchaseMemo;
        this.shippingMemo = shippingMemo;
        this.adminMemo = adminMemo;
    }

}
