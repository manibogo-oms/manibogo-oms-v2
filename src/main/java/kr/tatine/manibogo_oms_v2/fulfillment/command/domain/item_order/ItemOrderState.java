package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemOrderState {

    PLACED("주문"),
    PURCHASED("발주"),
    DISPATCHED("출고"),
    SHIPPED("배송"),
    CONFIRMED("확정"),
    CANCELLED("취소"),
    REFUNDED("환불");

    private final String description;

    public boolean canProceedTo(ItemOrderState state) {
        return this.ordinal() < state.ordinal();
    }

    public boolean isAfter(ItemOrderState state) {
        return this.ordinal() > state.ordinal();
    }

}
