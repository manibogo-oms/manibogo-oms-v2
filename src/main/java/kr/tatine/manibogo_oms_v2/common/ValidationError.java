package kr.tatine.manibogo_oms_v2.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class ValidationError {

    private final String name;

    private final String errorCode;

    private ValidationError(String name, String errorCode) {
        this.name = name;
        this.errorCode = errorCode;
    }

    public static ValidationError of(String code) {
        return new ValidationError(null, code);
    }

    public static ValidationError of(String name, String code) {
        return new ValidationError(name, code);
    }
}
