package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DetailSearchParam implements Describable {

    ITEM_ORDER_NUMBER("상품주문번호"),
    ORDER_NUMBER("주문번호"),
    CUSTOMER_NAME("구매자명"),
    CUSTOMER_PHONE_NUMBER("구매자연락처"),
    RECIPIENT_NAME("수취인명"),
    RECIPIENT_PRIMARY_PHONE_NUMBER("수취인연락처1"),
    RECIPIENT_SECONDARY_PHONE_NUMBER("수취인연락처2"),
    DELIVERY_ADDRESS("주소"),
    SALES_PRODUCT_NUMBER("판매처 번호"),
    DELIVERY_TRACKING_NUMBER("송장번호");

    private final String description;

}
