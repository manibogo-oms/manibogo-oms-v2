package kr.tatine.manibogo_oms_v2.common.converter;

import kr.tatine.manibogo_oms_v2.common.model.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringToOptionListConvertor {

    private StringToOptionListConvertor() {}

    public static List<Option> convert(String string) {

        if (Objects.isNull(string) || string.isBlank()) return List.of();

        final String[] optionStrings = string.trim().split("/");

        final List<Option> options = new ArrayList<>();

        for (String optionString : optionStrings) {
            final String[] tokens = optionString.trim().split(": ");

            if (tokens.length != 2) {
                throw new InvalidOptionFormatException();
            }

            final Option option = new Option(tokens[0], tokens[1]);

            options.add(option);
        }

        return options;
    }

}
