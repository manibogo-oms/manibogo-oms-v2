package kr.tatine.manibogo_oms_v2.common;

import java.util.regex.Pattern;

public class Validator {

    private Validator() {}

    private static final String PHONE_NUMBER_REGEXP = "^\\d{2,4}-\\d{3,4}-\\d{4}$";

    public static boolean isInvalidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) return true;

        return !Pattern.matches(PHONE_NUMBER_REGEXP, phoneNumber);
    }

}
