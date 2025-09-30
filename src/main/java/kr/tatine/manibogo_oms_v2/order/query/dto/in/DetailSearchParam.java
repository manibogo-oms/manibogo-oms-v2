package kr.tatine.manibogo_oms_v2.order.query.dto.in;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DetailSearchParam implements Describable {

    ORDER_NUMBER("주문번호"),
    CUSTOMER_NAME("구매자명"),
    CUSTOMER_TEL("구매자연락처"),
    RECIPIENT_NAME("수취인명"),
    RECIPIENT_TEL_1("수취인연락처1"),
    RECIPIENT_TEL_2("수취인연락처2"),
    RECIPIENT_ADDRESS("주소"),
    SHIPPING_TRACKING_NUMBER("송장번호"),
    SHIPPING_BUNDLE_NUMBER("묶음배송번호");

    private final String description;

}
