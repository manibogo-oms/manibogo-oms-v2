package kr.tatine.manibogo_oms_v2.juso.application.service;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoLookupPort;
import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.common.model.Address;
import kr.tatine.manibogo_oms_v2.juso.domain.Juso;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.JusoQueryPort;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.JusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LookupJusoService implements JusoLookupPort {

    private final List<JusoQueryPort> queryPorts;

    private final JusoStorePort storePort;

    @Override
    public Optional<JusoView> lookup(Address address) {
        for (final JusoQueryPort queryPort : queryPorts) {
            final Optional<JusoView> result = queryPort.findByAddress(address.getAddress1());
            if (result.isPresent()) {
                storePort.save(deserialize(result.get()));
                return result;
            }
        }
        return Optional.empty();
    }

    private static Juso deserialize(JusoView juso) {
        return new Juso(juso.jusoCode(), juso.admCode(), juso.address(), juso.sido(), juso.sigungu());
    }

}
