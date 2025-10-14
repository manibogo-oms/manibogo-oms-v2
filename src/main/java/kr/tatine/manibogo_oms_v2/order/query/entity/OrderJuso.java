package kr.tatine.manibogo_oms_v2.order.query.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import kr.tatine.manibogo_oms_v2.common.model.OrderNumber;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderJuso {

    @EmbeddedId
    private OrderNumber orderNumber;

    private JusoCode jusoCode;

    private String sido;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    public OrderJuso(OrderNumber orderNumber, JusoCode jusoCode, String sido) {
        this.orderNumber = orderNumber;
        this.jusoCode = jusoCode;
        this.sido = sido;
    }
}

