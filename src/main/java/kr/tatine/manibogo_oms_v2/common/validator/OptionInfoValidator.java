package kr.tatine.manibogo_oms_v2.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kr.tatine.manibogo_oms_v2.common.converter.InvalidOptionFormatException;
import kr.tatine.manibogo_oms_v2.common.converter.StringToOptionIdListConvertor;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionId;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class OptionInfoValidator implements ConstraintValidator<OptionInfo, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {

            final List<OptionId> optionIds =
                    StringToOptionIdListConvertor.convert(value);

            return Objects.nonNull(optionIds);

        } catch (InvalidOptionFormatException exception) {

            return false;
        }
    }

}
