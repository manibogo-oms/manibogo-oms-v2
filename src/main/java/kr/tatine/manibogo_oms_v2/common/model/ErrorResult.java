package kr.tatine.manibogo_oms_v2.common.model;

import lombok.Getter;

import java.util.*;

@Getter
public class ErrorResult {

    private final Map<String, FieldError> fieldErrors = new HashMap<>();

    private final List<GlobalError> globalErrors = new ArrayList<>();

    public void rejectValue(String fieldName, ErrorLevel errorLevel, String errorCode, Object[] arguments) {

        if (fieldErrors.containsKey(fieldName)) {
            fieldErrors.put(fieldName, fieldErrors.get(fieldName).addError(errorLevel, new Message(errorCode, arguments)));
            return;
        }

        fieldErrors.put(fieldName, new FieldError(
                errorLevel, Collections.singletonList(new Message(errorCode, arguments))));
    }

    public void rejectValue(String fieldName, ErrorLevel errorLevel, String errorCode) {
        rejectValue(fieldName, errorLevel, errorCode, new Object[0]);
    }

    public void rejectValue(String fieldName, String errorCode,  Object[] arguments) {
        rejectValue(fieldName, ErrorLevel.ERROR, errorCode, arguments);
    }

    public void rejectValue(String fieldName, String errorCode) {
        rejectValue(fieldName, ErrorLevel.ERROR, errorCode, new Object[0]);
    }

    public void reject(ErrorLevel errorLevel, String errorCode, Object[] arguments) {
        globalErrors.add(new GlobalError(errorLevel, new Message(errorCode, arguments)));
    }

    public void reject(String errorCode, Object[] arguments) {
        reject(ErrorLevel.ERROR, errorCode, new Object[0]);
    }

    public void reject(ErrorLevel errorLevel, String errorCode) {
        reject(errorLevel, errorCode, new Object[0]);
    }

    public void reject(String errorCode) {
        reject(ErrorLevel.ERROR, errorCode, new Object[0]);
    }

    public boolean hasGlobalError() {
        return !globalErrors.isEmpty();
    }

    public FieldError getFieldError(String fieldName) {
        return fieldErrors.get(fieldName);
    }

}
