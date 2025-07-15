package kr.tatine.manibogo_oms_v2.common.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {

    private String phoneNumber = "";

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
