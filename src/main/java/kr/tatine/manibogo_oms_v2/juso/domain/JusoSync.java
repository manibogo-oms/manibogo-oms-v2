package kr.tatine.manibogo_oms_v2.juso.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@ToString
@Immutable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class JusoSync {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate referenceDate;

    private String resultCode;

    private String resultMessage;

    @OneToMany(mappedBy = "integration")
    private List<Juso> jusos;

    @CreatedDate
    private LocalDateTime createdAt;

    public JusoSync(LocalDate referenceDate) {
        this.referenceDate = referenceDate;
    }

    public JusoSync(LocalDate referenceDate, String resultCode, String resultMessage) {
        this.referenceDate = referenceDate;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

}
