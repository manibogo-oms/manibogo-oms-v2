package kr.tatine.manibogo_oms_v2.common.utils;

public final class EnumI18n {

    private EnumI18n() {}

    public static String key(Enum<?> e) {
        final String enumType = toKebab(e.getDeclaringClass().getSimpleName());
        final String enumValue = toKebab(e.name());

        return "enum." + enumType + "." + enumValue;
    }

    private static String toKebab(String s) {
        return s.replaceAll("([a-z0-9])([A-Z])", "$1-$2").replace('_','-').toLowerCase();
    }

}
