package kr.tatine.manibogo_oms_v2.juso.application.dto.out;


import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;

import java.util.List;

public record JusoDelta(String code, String message, List<JusoView> result) { }
