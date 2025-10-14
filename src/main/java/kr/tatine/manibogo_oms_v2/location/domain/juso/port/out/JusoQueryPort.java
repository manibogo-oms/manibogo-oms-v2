package kr.tatine.manibogo_oms_v2.location.domain.juso.port.out;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;

import java.util.Optional;

public interface JusoQueryPort {

    Optional<JusoView> findByAddress(String address);

}
