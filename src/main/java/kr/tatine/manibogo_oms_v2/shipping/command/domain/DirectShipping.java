package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DirectShipping extends Shipping {

    public DirectShipping(ShippingNumber number, ChargeType chargeType, Recipient recipient) {
        super(number, chargeType, recipient);
    }

    @Override
    public void complete() {

    }

    @Override
    protected boolean isSameMethod(Shipping shipping) {
        return shipping instanceof DirectShipping;
    }
}
