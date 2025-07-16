package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.tatine.manibogo_oms_v2.region.command.domain.Address;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipient {

    @Column(name = "recipient_name")
    private String name;

    @AttributeOverride(
            name = "phoneNumber",
            column = @Column(name = "recipient_phone_number_1"))
    private PhoneNumber phoneNumber1;

    @AttributeOverride(
            name = "phoneNumber",
            column = @Column(name = "recipient_phone_number_2"))
    private PhoneNumber phoneNumber2;

    private Address address;

    public Recipient(String name, PhoneNumber phoneNumber1, PhoneNumber phoneNumber2, Address address) {
        this.name = name;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.address = address;
    }
}
