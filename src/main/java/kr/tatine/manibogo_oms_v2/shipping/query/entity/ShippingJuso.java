package kr.tatine.manibogo_oms_v2.shipping.query.entity;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ShippingJuso {

    @EmbeddedId
    private ShippingNumber shippingNumber;

    @Column(updatable = false)
    private JusoCode jusoCode;

    private String address;

    private String admCode;

    private String sido;

    private String sigungu;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    public ShippingJuso(ShippingNumber shippingNumber, JusoCode jusoCode, String admCode, String sido, String sigungu) {
        this.shippingNumber = shippingNumber;
        this.jusoCode = jusoCode;
        this.admCode = admCode;
        this.sido = sido;
        this.sigungu = sigungu;
    }

    public void updateJuso(String address, String admCode, String sido, String sigungu) {
        this.address = address;
        this.admCode = admCode;
        this.sido = sido;
        this.sigungu = sigungu;
    }
}
