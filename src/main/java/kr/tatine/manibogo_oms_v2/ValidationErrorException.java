package kr.tatine.manibogo_oms_v2;

import kr.tatine.manibogo_oms_v2.common.ValidationError;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationErrorException extends RuntimeException {

    private final List<ValidationError> validationErrors;

    public ValidationErrorException(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
