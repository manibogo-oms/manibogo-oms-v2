package kr.tatine.manibogo_oms_v2.location.domain.juso.port.out;

import kr.tatine.manibogo_oms_v2.location.domain.juso.Juso;

import java.util.Optional;

public interface JusoQueryPort {

    Optional<Juso> findByAddress(String address);

}
