package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChargeType {

    COD("착불"),
    PREPAID("선결제");

    private final String description;

}
