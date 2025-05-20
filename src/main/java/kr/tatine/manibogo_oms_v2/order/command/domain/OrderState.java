package kr.tatine.manibogo_oms_v2.order.command.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderState {

    PLACED("주문"),
    PURCHASED("발주"),
    DISPATCHED("출고"),
    SHIPPED("배송"),
    CONFIRMED("확정"),
    CANCELED("취소"),
    REFUNDED("반품");

    private final String description;

}
