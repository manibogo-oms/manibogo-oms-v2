package kr.tatine.manibogo_oms_v2.juso.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Immutable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class JusoSync {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime referenceTime;

    private String resultCode;

    private String resultMessage;

    @CreatedDate
    private LocalDateTime createdAt;

    public JusoSync(LocalDateTime referenceTime, String resultCode, String resultMessage) {
        this.referenceTime = referenceTime;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

}
