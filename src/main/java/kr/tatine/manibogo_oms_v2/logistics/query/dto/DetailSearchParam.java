package kr.tatine.manibogo_oms_v2.logistics.query.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DetailSearchParam {

    SHIPPING_BUNDLE_NUMBER("묶음배송번호"),
    CUSTOMER_NAME("구매자명"),
    CUSTOMER_TEL("구매자 연락처"),
    RECIPIENT_NAME("수취인명"),
    RECIPIENT_TEL1("수취인 연락처1"),
    RECIPIENT_TEL2("수취인 연락처2"),
    ADDRESS("주소");

    private final String description;

}
