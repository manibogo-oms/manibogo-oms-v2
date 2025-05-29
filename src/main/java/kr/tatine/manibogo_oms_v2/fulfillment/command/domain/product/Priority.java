package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Priority {

    private Integer priority;

    public Priority(Integer priority) {
        this.priority = priority;
    }

}
