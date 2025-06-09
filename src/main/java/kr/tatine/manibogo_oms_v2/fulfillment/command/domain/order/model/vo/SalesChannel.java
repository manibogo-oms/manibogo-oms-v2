package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SalesChannel implements Describable {

    SMART_STORE("스마트스토어"),
    LOCAL("매장");

    private final String description;
}
