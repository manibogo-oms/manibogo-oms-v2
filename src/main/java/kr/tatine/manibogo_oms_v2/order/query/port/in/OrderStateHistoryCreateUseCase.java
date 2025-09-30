package kr.tatine.manibogo_oms_v2.order.query.port.in;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;

import java.time.LocalDateTime;

public interface OrderStateHistoryCreateUseCase {

    void create(OrderNumber orderNumber, LocalDateTime placedAt);

}
