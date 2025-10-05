package kr.tatine.manibogo_oms_v2.location.infra;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoLookupPort;
import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.location.domain.address.Address;
import kr.tatine.manibogo_oms_v2.location.domain.juso.Juso;
import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoQueryPort;
import kr.tatine.manibogo_oms_v2.location.domain.juso.JusoStorePort;
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
            final Optional<Juso> result = queryPort.findByAddress(address.getAddress1());
            if (result.isPresent()) {
                storePort.save(result.get());
                return result.map(LookupJusoService::toView);
            }
        }
        return Optional.empty();
    }

    private static JusoView toView(Juso juso) {
        return new JusoView(juso.getCode(), juso.getAdmCode(), juso.getAddress(), juso.getSido(), juso.getSigungu());
    }
}
