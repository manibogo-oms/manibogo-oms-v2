package kr.tatine.manibogo_oms_v2.shipping.query.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Juso {

    @Id
    String jusoCode;

    String admCode;

    String address;

    String sido;

    String sigungu;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    public Juso(String jusoCode, String admCode, String address, String sido, String sigungu) {
        this.jusoCode = jusoCode;
        this.admCode = admCode;
        this.address = address;
        this.sido = sido;
        this.sigungu = sigungu;
    }
}
