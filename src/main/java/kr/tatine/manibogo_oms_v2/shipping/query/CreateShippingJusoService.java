package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.Juso;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingJuso;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.JusoQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingAddressQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingJusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateShippingJusoService {

    private final ShippingAddressQueryPort addressQueryPort;

    private final JusoQueryPort jusoQueryPort;

    private final ShippingJusoStorePort storePort;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create(ShippingNumber shippingNumber) {

        final Address address = addressQueryPort
                .findByNumber(shippingNumber)
                .orElseThrow(() -> new RuntimeException(getBaseMessage(shippingNumber) + " 배송 객체를 찾을 수 없음"));

        final Juso juso = jusoQueryPort.findByAddress(address.getAddress1())
                .orElseThrow(() -> new RuntimeException(getBaseMessage(shippingNumber) + "주소 정보 조회 실패!"));

        final ShippingJuso shippingJuso =
                new ShippingJuso(shippingNumber, juso.jusoCode(), juso.admCode(), juso.address(), juso.sidoName(), juso.sigunguName());

        storePort.save(shippingJuso);
    }

    private static String getBaseMessage(ShippingNumber shippingNumber) {
        return "[%s] ".formatted(shippingNumber.getShippingNumber());
    }

}
