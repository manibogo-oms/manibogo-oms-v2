package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipient {

    private String name;

    @AttributeOverride(
            name = "phoneNumber",
            column = @Column(name = "recipient_phone_number_1"))
    private PhoneNumber phoneNumber1;

    @AttributeOverride(
            name = "phoneNumber",
            column = @Column(name = "recipient_phone_number_2"))
    private PhoneNumber phoneNumber2;

}
