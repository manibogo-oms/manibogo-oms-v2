package kr.tatine.manibogo_oms_v2.common.model;


import java.io.Serializable;

public record GlobalError(ErrorLevel level, Message message) implements Serializable {

}