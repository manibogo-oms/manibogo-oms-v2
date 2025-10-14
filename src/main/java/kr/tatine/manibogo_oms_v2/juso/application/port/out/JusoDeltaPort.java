package kr.tatine.manibogo_oms_v2.juso.application.port.out;

import kr.tatine.manibogo_oms_v2.juso.application.dto.out.JusoDelta;

import java.time.LocalDate;

public interface JusoDeltaPort {

    JusoDelta fetch(LocalDate lastIntegratedOn);

}
