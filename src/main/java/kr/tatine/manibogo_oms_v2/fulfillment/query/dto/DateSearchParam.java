package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DateSearchParam implements Describable {

    PLACED_AT("주문일자"),
    DISPATCH_DEADLINE("발송기한"),
    PREFERRED_SHIPS_ON("희망배송일자"),
    PURCHASED_ON("발주일자"),
    DISPATCHED_ON("출고일자"),
    SHIPPED_ON("배송일자"),
    CONFIRMED_ON("완료일자"),
    CANCELLED_ON("취소일자"),
    REFUNDED_ON("반품일자");

    private final String description;

}
