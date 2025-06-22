package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import io.hypersistence.tsid.TSID;
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

    public static ItemOrderNumber random() {
        return new ItemOrderNumber(TSID.Factory.getTsid().toString());
    }

}
