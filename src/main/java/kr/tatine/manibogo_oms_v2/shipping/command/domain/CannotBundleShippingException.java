package kr.tatine.manibogo_oms_v2.shipping.command.domain;

public class CannotBundleShippingException extends RuntimeException {
    public CannotBundleShippingException(String message) {
        super(message);
    }
}
