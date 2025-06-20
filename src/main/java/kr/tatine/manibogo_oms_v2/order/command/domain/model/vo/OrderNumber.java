package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

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
public class OrderNumber implements Serializable {

    private String orderNumber;

    public OrderNumber(String number) {
        this.orderNumber = number;
    }

}
