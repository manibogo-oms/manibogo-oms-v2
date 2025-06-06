package kr.tatine.manibogo_oms_v2.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String REGEXP = "^\\d{2,4}-\\d{3,4}-\\d{4}$";

    private boolean isNullable;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (isNullable && Strings.isBlank(value)) {
            return true;
        }

        return Pattern.matches(REGEXP, value);
    }

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        this.isNullable = constraintAnnotation.nullable();
    }
}
