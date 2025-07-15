package kr.tatine.manibogo_oms_v2.order.command.domain.event;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.product.command.domain.ProductNumber;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderPlacedEvent extends Event {

    private final String orderNumber;

    private final String productNumber;

    private final String productName;

    private final String shippingMethod;

    private final String shippingChargeType;

    public OrderPlacedEvent(OrderNumber orderNumber, ProductNumber productNumber, String productName, ShippingMethod shippingMethod, ChargeType shippingChargeType) {
        this.orderNumber = orderNumber.getOrderNumber();
        this.productNumber = productNumber.getProductNumber();
        this.productName = productName;
        this.shippingMethod = shippingMethod.name();
        this.shippingChargeType = shippingChargeType.name();
    }
}
