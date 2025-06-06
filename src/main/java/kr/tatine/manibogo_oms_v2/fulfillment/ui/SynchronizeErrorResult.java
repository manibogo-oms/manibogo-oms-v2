package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import java.util.List;

public record SynchronizeErrorResult(
        String itemOrderNumber,
        List<String> errors) {

    public record FieldError(String field, String message) {}
}
