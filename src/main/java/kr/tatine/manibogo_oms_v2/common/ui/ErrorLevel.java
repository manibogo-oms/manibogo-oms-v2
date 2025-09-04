package kr.tatine.manibogo_oms_v2.common.ui;

public enum ErrorLevel implements Comparable<ErrorLevel> {

    WARN, ERROR;

    public ErrorLevel getHigherLevel( ErrorLevel level) {
        if (this.ordinal() > level.ordinal()) return this;
        return level;
    }

}
