package kr.tatine.manibogo_oms_v2.fulfillment.ui;

public record AlertResult(
        AlertType type,
        String messageCode,
        Object[] arguments
) {

    public AlertResult(AlertType type, String messageCode) {
        this(type, messageCode, new Object[0]);
    }

    public enum AlertType {
        SUCCESS, ERROR
    }

}
