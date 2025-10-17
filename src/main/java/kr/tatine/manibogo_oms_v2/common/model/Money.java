package kr.tatine.manibogo_oms_v2.common.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

    private Long value;

    public Money(Long value) {
        this.value = value;
    }

}
