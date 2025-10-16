package kr.tatine.manibogo_oms_v2.order.query.port.in;

import kr.tatine.manibogo_oms_v2.order.query.dto.in.SyncOrderJusoCommand;

public interface SyncOrderJusoUseCase {

    void synchronize(SyncOrderJusoCommand command);

}
