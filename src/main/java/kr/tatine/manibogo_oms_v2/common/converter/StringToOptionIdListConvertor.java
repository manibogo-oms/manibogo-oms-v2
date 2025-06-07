package kr.tatine.manibogo_oms_v2.common.converter;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.OptionId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringToOptionIdListConvertor {

    private StringToOptionIdListConvertor() {}

    public static List<OptionId> convert(String string) {

        if (Objects.isNull(string) || string.isBlank()) return List.of();

        final String[] optionStrings = string.trim().split("/");

        final List<OptionId> optionIds = new ArrayList<>();

        for (String optionString : optionStrings) {
            final String[] tokens = optionString.trim().split(": ");

            if (tokens.length != 2) {
                throw new InvalidOptionFormatException();
            }

            final OptionId optionId = new OptionId(tokens[0], tokens[1]);

            optionIds.add(optionId);
        }

        return optionIds;
    }

}
