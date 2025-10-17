package kr.tatine.manibogo_oms_v2.shipping.query.dto.in;

import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;

public record SyncShippingJusoCommand(
        JusoCode jusoCode,
        String address,
        String admCode,
        String sido,
        String sigungu
) { }
