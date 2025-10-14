package kr.tatine.manibogo_oms_v2.order.infra;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoLookupPort;
import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.query.entity.OrderJuso;
import kr.tatine.manibogo_oms_v2.order.query.port.in.OrderJusoUpsertUseCase;
import kr.tatine.manibogo_oms_v2.order.query.port.out.OrderAddressQueryPort;
import kr.tatine.manibogo_oms_v2.order.query.port.out.OrderJusoStorePort;
import kr.tatine.manibogo_oms_v2.common.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpsertOrderJusoService implements OrderJusoUpsertUseCase {

    private final OrderJusoStorePort storePort;

    private final OrderAddressQueryPort addressQueryPort;

    private final JusoLookupPort jusoLookupPort;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void upsert(OrderNumber orderNumber) {

        final Address address = addressQueryPort
                .findByNumber(orderNumber)
                .orElseThrow(RuntimeException::new);

        final JusoView juso = jusoLookupPort.lookup(address)
                .orElseThrow(RuntimeException::new);

        storePort.save(new OrderJuso(orderNumber, juso.jusoCode(), juso.sido()));
    }
}
