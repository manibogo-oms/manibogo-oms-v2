package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.ChargeType;
import kr.tatine.manibogo_oms_v2.common.model.Recipient;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@ToString
@Getter(AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Shipping {

    @EmbeddedId
    private ShippingNumber number;

    @Enumerated(EnumType.STRING)
    private ShippingState state;

    @Enumerated(EnumType.STRING)
    private ChargeType chargeType;

    @Embedded
    private Recipient recipient;

    public Shipping(ShippingNumber number, ChargeType chargeType, Recipient recipient) {
        this.number = number;
        this.state = ShippingState.PREPARING;
        this.chargeType = chargeType;
        this.recipient = recipient;
    }

    public void dispatch() {}

    public abstract void complete();

    public void revert() {}

    public void bundle(final Shipping shipping) throws CannotBundleShippingException {

        if (isSameMethod(shipping)) {
            throw new CannotBundleShippingException("배송타입이 서로 다릅니다.");
        }

        if (!Objects.equals(getChargeType(), shipping.getChargeType())) {
            throw new CannotBundleShippingException("배송비 운임 타입이 다릅니다.");
        }

        if (!Objects.equals(getRecipient(), shipping.getRecipient())) {
            throw new CannotBundleShippingException("수취인 정보가 다릅니다.");
        }
    }

    protected abstract boolean isSameMethod(final Shipping shipping);


}
