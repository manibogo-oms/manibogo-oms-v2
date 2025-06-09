package kr.tatine.manibogo_oms_v2.common.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

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
