package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import kr.tatine.manibogo_oms_v2.common.model.Describable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChargeType implements Describable {

    COD("착불"),
    PREPAID("선결제");

    private final String description;

}
