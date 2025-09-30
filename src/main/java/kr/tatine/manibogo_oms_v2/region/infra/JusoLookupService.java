package kr.tatine.manibogo_oms_v2.region.infra;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoLookupPort;
import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import kr.tatine.manibogo_oms_v2.region.query.entity.Juso;
import kr.tatine.manibogo_oms_v2.region.query.port.out.JusoQueryPort;
import kr.tatine.manibogo_oms_v2.region.query.port.out.JusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JusoLookupService implements JusoLookupPort {

    private final List<JusoQueryPort> queryPorts;

    private final JusoStorePort storePort;

    @Override
    public Optional<Juso> lookup(Address address) {
        for (final JusoQueryPort queryPort : queryPorts) {
            final Optional<Juso> result = queryPort.findByAddress(address.getAddress1());
            if (result.isPresent()) {
                storePort.save(result.get());
                return result;
            }
        }
        return Optional.empty();
    }
}
