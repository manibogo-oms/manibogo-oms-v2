package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderLocation {

    SMART_STORE("스마트스토어"),
    LOCAL("매장");

    private final String description;

}
