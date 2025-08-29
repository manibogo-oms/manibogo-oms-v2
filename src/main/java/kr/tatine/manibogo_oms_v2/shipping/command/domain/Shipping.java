package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    private String recipientMessage;

    private String adminMemo;

    public Shipping(ShippingNumber number, ShippingState state, ChargeType chargeType, Recipient recipient, String recipientMessage, String adminMemo) {
        this.number = number;
        this.state = state;
        this.chargeType = chargeType;
        this.recipient = recipient;
        this.recipientMessage = recipientMessage;
        this.adminMemo = adminMemo;
    }

    public void dispatch() {}

    public abstract void complete();

    public void revert() {}


}
