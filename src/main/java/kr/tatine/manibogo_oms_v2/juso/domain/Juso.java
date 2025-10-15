package kr.tatine.manibogo_oms_v2.juso.domain;

import jakarta.persistence.*;
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

    @EmbeddedId
    private JusoCode code;

    private String admCode;

    private String address;

    private String sido;

    private String sigungu;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt;

    public Juso(JusoCode code, String admCode, String address, String sido, String sigungu) {
        this.code = code;
        this.admCode = admCode;
        this.address = address;
        this.sido = sido;
        this.sigungu = sigungu;
    }


}
