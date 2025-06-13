package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DateSearchParam implements Describable {

    SALES_STARTED_AT("주문일자"),
    DELIVERY_DEADLINE_AT("발송기한"),
    DELIVERY_PREFERRED_AT("희망배송일자"),
    SALES_PURCHASED_AT("발주일자"),
    SALES_DISPATCHED_AT("출고일자"),
    SALES_DELIVERED_AT("배송일자"),
    SALES_COMPLETED_AT("완료일자"),
    SALES_CANCELED_AT("취소일자"),
    SALES_REFUNDED_AT("반품일자");

    private final String description;

}
