package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.Entity;
import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.Recipient;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DirectShipping extends Shipping {

    public DirectShipping(ShippingNumber number, ChargeType chargeType, Recipient recipient, List<ShippingOrder> orders) {
        super(number, chargeType, recipient, orders);
    }

    @Override
    public void complete() {

    }

    @Override
    protected boolean isSameMethod(Shipping shipping) {
        return shipping instanceof DirectShipping;
    }
}
