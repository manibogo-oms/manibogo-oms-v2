package kr.tatine.manibogo_oms_v2.shipping.infra;

import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.Juso;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.JusoQueryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JusoApiClient implements JusoQueryPort {
    @Override
    public Optional<Juso> findByAddress(String address) {
        return Optional.empty();
    }
}
