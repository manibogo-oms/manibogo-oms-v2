package kr.tatine.manibogo_oms_v2.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kr.tatine.manibogo_oms_v2.common.converter.DescribableEnumConverter;
import kr.tatine.manibogo_oms_v2.common.converter.EnumDescriptionNotFoundException;
import kr.tatine.manibogo_oms_v2.common.converter.TargetIsNotEnumClassException;

public class DescribableEnumValidator implements ConstraintValidator<DescribableEnum, String> {

    private DescribableEnum annotation;

    @Override
    public void initialize(DescribableEnum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            DescribableEnumConverter.fromDescription(annotation.enumClazz(), value);
            return true;
        } catch (EnumDescriptionNotFoundException | TargetIsNotEnumClassException exception) {
            return false;
        }
    }
}
