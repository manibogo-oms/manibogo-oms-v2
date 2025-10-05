package kr.tatine.manibogo_oms_v2.common.contract.out;

import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoCode;

public record JusoView(
        JusoCode jusoCode,
        String admCode,
        String address,
        String sido,
        String sigungu
) { }
