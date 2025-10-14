package kr.tatine.manibogo_oms_v2.common.contract.out;

import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;

public record JusoView(
        JusoCode jusoCode,
        String admCode,
        String address,
        String sido,
        String sigungu
) { }
