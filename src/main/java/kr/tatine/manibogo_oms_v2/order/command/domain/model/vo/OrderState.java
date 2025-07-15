package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderState implements Describable {

    PLACED("주문"),
    PURCHASED("발주"),
    DISPATCHED("출고"),
    SHIPPED("배송"),
    CONFIRMED("확정"),
    CANCELLED("취소"),
    REFUNDED("환불");

    private final String description;

    public boolean canProceedTo(OrderState state) {
        return this.ordinal() < state.ordinal();
    }

    public boolean isAfter(OrderState state) {
        return this.ordinal() > state.ordinal();
    }

    public boolean isAfterOrSame(OrderState state) {
        return this.ordinal() >= state.ordinal();
    }

    public boolean isBefore(OrderState state) {
        return this.ordinal() < state.ordinal();
    }
    
}
