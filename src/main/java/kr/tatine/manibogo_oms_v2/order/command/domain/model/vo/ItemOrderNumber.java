package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOrderNumber implements Serializable {

    private String itemOrderNumber;

    public ItemOrderNumber(String number) {
        this.itemOrderNumber = number;
    }

}
