package kr.tatine.manibogo_oms_v2.shipping.query;

import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;

import java.util.Optional;

public interface OrderShippingViewDao {
    
    Optional<OrderShippingView> findByOrderNumber(OrderNumber orderNumber);

}
