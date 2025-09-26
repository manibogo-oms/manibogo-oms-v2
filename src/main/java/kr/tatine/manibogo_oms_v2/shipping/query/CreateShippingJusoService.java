package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.Juso;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingJusoView;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.JusoQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingJusoStorePort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateShippingJusoService {

    private final ShippingQueryPort shippingQueryPort;

    private final JusoQueryPort jusoQueryPort;

    private final ShippingJusoStorePort storePort;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create(ShippingNumber shippingNumber) {

        final ShippingView shippingView = shippingQueryPort
                .findByNumber(shippingNumber)
                .orElseThrow(() -> new RuntimeException(getBaseMessage(shippingNumber) + " 배송 객체를 찾을 수 없음"));

        final Juso juso = jusoQueryPort.findByAddress(shippingView.address1())
                .orElseThrow(() -> new RuntimeException(getBaseMessage(shippingNumber) + "주소 정보 조회 실패!"));

        final ShippingJusoView shippingJusoView =
                new ShippingJusoView(shippingNumber, juso.jusoCode(), juso.admCode(), juso.address(), juso.sidoName(), juso.sigunguName());

        storePort.save(shippingJusoView);
    }

    private static String getBaseMessage(ShippingNumber shippingNumber) {
        return "[%s] ".formatted(shippingNumber.getShippingNumber());
    }

}
