package kr.tatine.manibogo_oms_v2.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PhoneNumberValidator.class})
public @interface PhoneNumber {

    String message() default "{phoneNumber}";

    boolean nullable() default false;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}