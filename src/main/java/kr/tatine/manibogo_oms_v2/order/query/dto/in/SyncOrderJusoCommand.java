package kr.tatine.manibogo_oms_v2.order.query.dto.in;

import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;

public record SyncOrderJusoCommand(
        JusoCode jusoCode,
        String sido
) { }
