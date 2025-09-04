package kr.tatine.manibogo_oms_v2.common.ui;


import kr.tatine.manibogo_oms_v2.common.model.Message;

import java.io.Serializable;

public record GlobalError(ErrorLevel level, Message message) implements Serializable {

}