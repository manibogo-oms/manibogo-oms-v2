package kr.tatine.manibogo_oms_v2.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import kr.tatine.manibogo_oms_v2.common.model.Describable;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DescribableEnumValidator.class)
public @interface DescribableEnum {

    String message() default "{describableEnum}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<? extends Describable> enumClazz();

}
