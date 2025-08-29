package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingInfo {

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_method")
    private ShippingMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_charge_type")
    private ChargeType chargeType;

    @Column(name = "recipient_name")
    private String recipientName;

    @AttributeOverride(
            name = "phoneNumber",
            column = @Column(name = "recipient_tel1"))
    private PhoneNumber recipientTel1;

    @AttributeOverride(
            name = "phoneNumber",
            column = @Column(name = "recipient_tel2"))
    private PhoneNumber recipientTel2;

    @AttributeOverrides({
            @AttributeOverride(
                    name = "address1", column = @Column(name = "shipping_addr1")),
            @AttributeOverride(
                    name = "address2", column = @Column(name = "shipping_addr2")),
            @AttributeOverride(
                    name = "zipCode", column = @Column(name = "shipping_zip_code"))
    })
    private Address address;

    public ShippingInfo(ShippingMethod method, ChargeType chargeType, String recipientName, PhoneNumber recipientTel1, PhoneNumber recipientTel2, Address address) {
        this.method = method;
        this.chargeType = chargeType;
        this.recipientName = recipientName;
        this.recipientTel1 = recipientTel1;
        this.recipientTel2 = recipientTel2;
        this.address = address;
    }
}
