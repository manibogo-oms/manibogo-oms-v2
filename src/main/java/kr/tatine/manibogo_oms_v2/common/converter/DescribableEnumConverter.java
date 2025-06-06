package kr.tatine.manibogo_oms_v2.common.converter;

import kr.tatine.manibogo_oms_v2.common.model.Describable;

public class DescribableEnumConverter {

    private DescribableEnumConverter() {}

    public static <T extends Describable> T fromDescription(
            final Class<T> clazz, final String description) {

        final T[] enumConstants = clazz.getEnumConstants();

        if (enumConstants == null) {
            throw new TargetIsNotEnumClassException();
        }

        for (T enumConstant : enumConstants) {
            if (enumConstant.getDescription().equals(description)) {
                return enumConstant;
            }
        }

        throw new EnumDescriptionNotFoundException();
    }

}
