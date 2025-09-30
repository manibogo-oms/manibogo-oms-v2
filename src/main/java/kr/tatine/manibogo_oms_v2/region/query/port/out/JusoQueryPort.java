package kr.tatine.manibogo_oms_v2.region.query.port.out;

import kr.tatine.manibogo_oms_v2.region.query.entity.Juso;

import java.util.Optional;

public interface JusoQueryPort {

    Optional<Juso> findByAddress(String address);

}
