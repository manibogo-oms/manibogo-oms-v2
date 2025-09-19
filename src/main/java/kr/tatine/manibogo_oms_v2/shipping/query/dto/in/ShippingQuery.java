package kr.tatine.manibogo_oms_v2.shipping.query.dto.in;

import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingState;

public record ShippingQuery(
        DetailSearchType detailSearchType,
        String keyword,
        ShippingState state,
        String sido,
        String sigungu
) { }
