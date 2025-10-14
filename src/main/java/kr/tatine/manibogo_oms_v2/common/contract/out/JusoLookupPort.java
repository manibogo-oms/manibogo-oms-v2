package kr.tatine.manibogo_oms_v2.common.contract.out;

import kr.tatine.manibogo_oms_v2.common.model.Address;

import java.util.Optional;

public interface JusoLookupPort {

    Optional<JusoView> lookup(Address address);

}
