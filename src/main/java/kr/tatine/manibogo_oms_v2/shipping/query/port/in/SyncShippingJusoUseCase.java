package kr.tatine.manibogo_oms_v2.shipping.query.port.in;

import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.SyncShippingJusoCommand;

public interface SyncShippingJusoUseCase {

    void synchronize(SyncShippingJusoCommand command);

}
