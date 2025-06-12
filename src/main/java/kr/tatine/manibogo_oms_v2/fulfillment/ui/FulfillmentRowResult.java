package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import java.util.List;

public record FulfillmentRowResult(
            RowState rowState,
            String messageCode,
            List<FieldError> fieldErrors
    ) {

        public FulfillmentRowResult(RowState rowState, String messageCode) {
            this(rowState, messageCode, List.of());
        }

        public enum RowState {
            SUCCESS, SKIP, ERROR
        }

        public record FieldError(
                String fieldName, String errorCode, Object[] arguments) {

            public FieldError(String fieldName, String errorCode) {
                this(fieldName, errorCode, new Object[0]);
            }
        }

    }