package kr.tatine.manibogo_oms_v2.order.query.port.in;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;

public interface OrderJusoUpsertUseCase {

    void upsert(OrderNumber orderNumber);

}
