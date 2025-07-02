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
public class OrderNumber implements Serializable {

    private String orderNumber;

    public OrderNumber(String number) {
        this.orderNumber = number;
    }

    public static OrderNumber random() {
        return new OrderNumber(TSID.Factory.getTsid().toString());
    }

}
