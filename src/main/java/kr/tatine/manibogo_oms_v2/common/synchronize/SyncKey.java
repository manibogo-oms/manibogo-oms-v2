package kr.tatine.manibogo_oms_v2.common.synchronize;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SyncKey {

    private String subscriberId;

    private String providerId;

    private String feed;

    public SyncKey(String subscriberId, String providerId, String feed) {
        this.subscriberId = subscriberId;
        this.providerId = providerId;
        this.feed = feed;
    }
}
