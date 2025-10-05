package kr.tatine.manibogo_oms_v2.location.domain.region;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionCode implements Serializable {

    private String code;

    public RegionCode(String code) {
        this.code = code;
    }
}
