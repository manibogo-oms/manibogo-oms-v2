package kr.tatine.manibogo_oms_v2.juso.application.port.out;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;

import java.time.LocalDate;
import java.util.List;

public interface IntegratedJusoQueryPort {

    List<JusoView> findAllByIntegratedFrom(LocalDate integratedFrom);

}
