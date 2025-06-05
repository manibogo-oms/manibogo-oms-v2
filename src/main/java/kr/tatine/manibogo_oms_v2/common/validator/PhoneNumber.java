package kr.tatine.manibogo_oms_v2.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PhoneNumberValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "올바르지 않은 형식입니다.";

    boolean nullable() default false;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}