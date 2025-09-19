package kr.tatine.manibogo_oms_v2.shipping.command.application.port.out;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.shipping.command.application.dto.OrderSnapshot;

import java.util.Optional;

public interface OrderSnapshotQueryPort {
    
    Optional<OrderSnapshot> findByNumber(OrderNumber orderNumber);

}
