package kr.tatine.manibogo_oms_v2.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kr.tatine.manibogo_oms_v2.common.converter.InvalidOptionFormatException;
import kr.tatine.manibogo_oms_v2.common.converter.StringToOptionListConvertor;
import kr.tatine.manibogo_oms_v2.common.model.Option;

import java.util.List;
import java.util.Objects;

public class OptionInfoValidator implements ConstraintValidator<OptionInfo, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {

            final List<Option> options =
                    StringToOptionListConvertor.convert(value);

            return Objects.nonNull(options);

        } catch (InvalidOptionFormatException exception) {

            return false;
        }
    }

}
