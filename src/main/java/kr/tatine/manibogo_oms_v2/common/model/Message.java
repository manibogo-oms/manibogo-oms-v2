package kr.tatine.manibogo_oms_v2.common.model;

import java.io.Serializable;

public record Message(String messageCode, Object[] arguments) implements Serializable { }
