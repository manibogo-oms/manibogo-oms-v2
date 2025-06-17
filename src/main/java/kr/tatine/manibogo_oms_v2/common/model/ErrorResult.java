package kr.tatine.manibogo_oms_v2.common.model;

import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

@Getter
public class ErrorResult {

    private final MultiValueMap<String, ErrorObject> fieldErrors = new LinkedMultiValueMap<>();

    private final List<ErrorObject> globalErrors = new ArrayList<>();

    public void rejectValue(String fieldName, ErrorLevel errorLevel, String errorCode, Object[] arguments) {
        fieldErrors.add(fieldName, new ErrorObject(errorLevel, new Message(errorCode, arguments)));
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
        globalErrors.add(new ErrorObject(errorLevel, new Message(errorCode, arguments)));
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

    public List<ErrorObject> getFieldErrors(String fieldName) {
        return fieldErrors.getOrDefault(fieldName, List.of());
    }

    public ErrorLevel getHighestErrorLevel(String fieldName) {
        if (!fieldErrors.containsKey(fieldName)) return ErrorLevel.NONE;

        return fieldErrors.get(fieldName).stream()
                .max(Comparator.comparing(ErrorObject::level))
                .map(ErrorObject::level)
                .orElse(ErrorLevel.NONE);

    }


    public ErrorLevel getHighestErrorLevelInObject(String objectName) {
        return fieldErrors.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(objectName))
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .max(Comparator.comparing(ErrorObject::level))
                .map(ErrorObject::level)
                .orElse(ErrorLevel.NONE);
    }

}
