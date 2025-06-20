package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

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

    @Column(name = "customer_name")
    private String name;

    @AttributeOverride(
            name = "phoneNumber",
            column = @Column(name = "customer_phone_number"))
    private PhoneNumber phoneNumber;

    @Column(name = "customer_message")
    private String message;

    public Customer(String name, PhoneNumber phoneNumber, String message) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
