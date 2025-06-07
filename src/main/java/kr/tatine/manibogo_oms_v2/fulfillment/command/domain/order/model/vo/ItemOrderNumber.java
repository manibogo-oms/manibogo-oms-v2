package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

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
