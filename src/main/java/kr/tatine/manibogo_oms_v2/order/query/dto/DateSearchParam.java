package kr.tatine.manibogo_oms_v2.order.query.dto;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DateSearchParam implements Describable {

    PLACED_AT("주문일자"),
    DISPATCH_DEADLINE("발송기한"),
    PREFERRED_SHIPS_ON("희망배송일자"),
    PURCHASED_AT("발주일자"),
    DISPATCHED_AT("출고일자"),
    SHIPPED_AT("배송일자"),
    CONFIRMED_AT("완료일자"),
    CANCELLED_AT("취소일자"),
    REFUNDED_AT("반품일자");

    private final String description;

}
