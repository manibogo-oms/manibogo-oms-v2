package kr.tatine.manibogo_oms_v2.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {OptionInfoValidator.class})
public @interface OptionInfo {

    String message() default "{optionInfo}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
