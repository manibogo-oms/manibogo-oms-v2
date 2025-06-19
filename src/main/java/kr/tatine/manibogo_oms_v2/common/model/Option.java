package kr.tatine.manibogo_oms_v2.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Option implements Serializable {

    @Column(name = "option_key")
    private String key;

    @Column(name = "option_value")
    private String value;

    public Option(String key, String value) {
        this.key = key.trim();
        this.value = value.trim();
    }

}
