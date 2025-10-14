package kr.tatine.manibogo_oms_v2.juso.application.port.in;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;

import java.time.LocalDate;
import java.util.List;

public interface FindJusoUseCase {

    List<JusoView> find(LocalDate integratedFrom);

}
