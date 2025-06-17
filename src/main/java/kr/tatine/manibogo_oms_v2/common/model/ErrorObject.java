package kr.tatine.manibogo_oms_v2.common.model;

public record ErrorObject(
            ErrorLevel level,
            Message message
) {}