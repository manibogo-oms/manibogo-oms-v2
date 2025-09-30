package kr.tatine.manibogo_oms_v2.common.contract.out;

import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import kr.tatine.manibogo_oms_v2.region.query.entity.Juso;

import java.util.Optional;

public interface JusoLookupPort {

    Optional<Juso> lookup(Address address);

}
