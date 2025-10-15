package kr.tatine.manibogo_oms_v2.common.synchronize;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Synchronize {

    @EmbeddedId
    private SyncKey key;

    @Getter
    private LocalDate referenceDate;

    private String resultMessage;

    private SyncStatus lastStatus;

    @CreatedDate
    private LocalDateTime createdOn;

    @LastModifiedDate
    private LocalDateTime lastModifiedOn;

    public Synchronize(SyncKey key, LocalDate referenceDate) {
        this.key = key;
        this.referenceDate = referenceDate;
        this.lastStatus = SyncStatus.RUNNING;
    }

    public void advance(SyncStatus syncStatus, String resultMessage) {
        this.lastStatus = syncStatus;
        this.resultMessage = resultMessage;
    }

}
