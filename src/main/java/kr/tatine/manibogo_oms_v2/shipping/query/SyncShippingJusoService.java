package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.SyncShippingJusoCommand;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingJuso;
import kr.tatine.manibogo_oms_v2.shipping.query.port.in.SyncShippingJusoUseCase;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingJusoQueryPort;
import kr.tatine.manibogo_oms_v2.shipping.query.port.out.ShippingJusoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SyncShippingJusoService implements SyncShippingJusoUseCase {

    private final ShippingJusoQueryPort shippingJusoQueryPort;

    private final ShippingJusoStorePort shippingJusoStorePort;

    @Override
    @Transactional
    public void synchronize(SyncShippingJusoCommand command) {

        final List<ShippingJuso> jusos = shippingJusoQueryPort
                .findAllByJusoCode(command.jusoCode());

        if (jusos.isEmpty()) return;

        jusos.forEach(shippingJuso -> shippingJuso.updateJuso(
                command.address(), command.admCode(), command.sido(), command.sigungu()
        ));

        shippingJusoStorePort.saveAll(jusos);
    }

}
