package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.Recipient;
import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("DIRECT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DirectShipping extends Shipping {

    public DirectShipping(ShippingNumber number, ChargeType chargeType, Recipient recipient, String customerMessage) {
        super(number, chargeType, recipient, customerMessage);
    }

    @Override
    protected boolean isSameMethod(final ShippingMethod method) {
        return method.equals(ShippingMethod.DIRECT);
    }
}
