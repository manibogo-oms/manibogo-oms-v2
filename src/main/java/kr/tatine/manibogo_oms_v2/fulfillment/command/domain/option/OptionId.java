package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionId implements Serializable {

    @Column(name = "option_key")
    private String key;

    @Column(name = "option_value")
    private String value;

    public OptionId(String key, String value) {
        this.key = key;
        this.value = value;
    }

    String getValue() {
        return value;
    }
}
