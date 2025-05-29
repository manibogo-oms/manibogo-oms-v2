package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.item_order;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StateHistory {

    @Enumerated(EnumType.STRING)
    private ItemOrderState changedTo;

    private LocalDate changedOn;

    public StateHistory(ItemOrderState changedTo, LocalDate changedOn) {
        this.changedTo = changedTo;
        this.changedOn = changedOn;
    }

}
