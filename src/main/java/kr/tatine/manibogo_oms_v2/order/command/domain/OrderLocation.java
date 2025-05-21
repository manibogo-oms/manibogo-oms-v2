package kr.tatine.manibogo_oms_v2.order.command.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderLocation {

    SMARTSTORE("스마트스토어"),
    LOCAL("매장");

    private final String description;

}
