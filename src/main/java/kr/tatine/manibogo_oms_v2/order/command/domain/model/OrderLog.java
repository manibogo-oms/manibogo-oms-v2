package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderNumber;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OrderNumber orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderState previousState;

    @Enumerated(EnumType.STRING)
    private OrderState newState;

    private LocalDateTime changedAt;

    private String changedBy;

    public OrderLog(
            OrderNumber orderNumber,
            OrderState previousState,
            OrderState newState,
            LocalDateTime changedAt,
            String changedBy
    ) {

        this.orderNumber = orderNumber;
        this.previousState = previousState;
        this.newState = newState;
        this.changedAt = changedAt;
        this.changedBy = changedBy;
    }

}
