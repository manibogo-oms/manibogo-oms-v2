package kr.tatine.manibogo_oms_v2.location.domain.juso.dto;

import kr.tatine.manibogo_oms_v2.location.domain.juso.Juso;

import java.util.List;

public record JusoDelta(String code, String message, List<Juso> result) { }
