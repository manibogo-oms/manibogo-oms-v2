package kr.tatine.manibogo_oms_v2.common.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

    private BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money(long value) {
        this(BigDecimal.valueOf(value));
    }
}
