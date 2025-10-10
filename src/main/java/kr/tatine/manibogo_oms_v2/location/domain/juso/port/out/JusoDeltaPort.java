package kr.tatine.manibogo_oms_v2.location.domain.juso.port.out;

import kr.tatine.manibogo_oms_v2.location.domain.juso.dto.JusoDelta;

import java.time.LocalDate;

public interface JusoDeltaPort {

    JusoDelta fetch(LocalDate lastIntegratedOn);

}
