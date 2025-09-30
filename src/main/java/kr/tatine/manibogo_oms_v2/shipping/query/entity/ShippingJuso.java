package kr.tatine.manibogo_oms_v2.shipping.query.entity;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
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

    private String jusoCode;

    private String admCode;

    private String address;

    private String sidoName;

    private String sigunguName;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    public ShippingJuso(ShippingNumber shippingNumber, String jusoCode, String admCode, String address, String sidoName, String sigunguName) {
        this.shippingNumber = shippingNumber;
        this.jusoCode = jusoCode;
        this.admCode = admCode;
        this.address = address;
        this.sidoName = sidoName;
        this.sigunguName = sigunguName;
    }
}
