package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.tatine.manibogo_oms_v2.common.model.PhoneNumber;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    private String name;

    @AttributeOverride(
            name = "phoneNumber",
            column = @Column(name = "customer_phone_number"))
    private PhoneNumber phoneNumber;

    public Customer(String name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
