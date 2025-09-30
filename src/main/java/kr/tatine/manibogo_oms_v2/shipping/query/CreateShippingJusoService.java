package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.Juso;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingJuso;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.JusoQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.JusoStorePort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingAddressQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingJusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateShippingJusoService {

    private final ShippingAddressQueryPort addressQueryPort;

    private final List<JusoQueryPort> jusoQueryPorts;

    private final JusoStorePort jusoStorePort;

    private final ShippingJusoStorePort shippingJusoStorePort;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create(ShippingNumber shippingNumber) {

        final Address address = addressQueryPort
                .findByNumber(shippingNumber)
                .orElseThrow(() -> new RuntimeException(getBaseMessage(shippingNumber) + " 배송 객체를 찾을 수 없음"));

        final Juso juso =  find(jusoQueryPorts, address)
                .orElseThrow(() -> new RuntimeException(getBaseMessage(shippingNumber) + "주소 정보 조회 실패!"));

        final ShippingJuso shippingJuso =
                new ShippingJuso(shippingNumber, juso.getJusoCode());

        jusoStorePort.save(juso);
        shippingJusoStorePort.save(shippingJuso);
    }

    private static Optional<Juso> find(List<JusoQueryPort> queryPorts, Address address) {
        for (final JusoQueryPort queryPort : queryPorts) {
            final Optional<Juso> result = queryPort.findByAddress(address.getAddress1());
            if (result.isPresent()) return result;
        }
        return Optional.empty();
    }

    private static String getBaseMessage(ShippingNumber shippingNumber) {
        return "[%s] ".formatted(shippingNumber.getShippingNumber());
    }

}
