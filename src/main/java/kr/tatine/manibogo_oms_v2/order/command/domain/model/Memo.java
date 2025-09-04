package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    private String purchaseMemo;

    private String shippingMemo;

    private String adminMemo;

    public Memo(String purchaseMemo, String shippingMemo, String adminMemo) {
        this.purchaseMemo = purchaseMemo;
        this.shippingMemo = shippingMemo;
        this.adminMemo = adminMemo;
    }

}
