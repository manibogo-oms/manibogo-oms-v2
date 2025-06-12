package kr.tatine.manibogo_oms_v2.common.model;

import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResult {

    private final MultiValueMap<String, Message> fieldErrors = new LinkedMultiValueMap<>();

    private final List<Message> globalErrors = new ArrayList<>();

    public void rejectValue(String fieldName, String errorCode, Object[] arguments) {
        fieldErrors.add(fieldName, new Message(errorCode, arguments));
    }

    public void rejectValue(String fieldName, String errorCode) {
        rejectValue(fieldName, errorCode, new Object[0]);
    }

    public void reject(String errorCode, Object[] arguments) {
        globalErrors.add(new Message(errorCode, arguments));
    }

    public void reject(String errorCode) {
        reject(errorCode, new Object[0]);
    }

    public boolean hasGlobalError() {
        return !globalErrors.isEmpty();
    }

    public List<Message> getFieldErrors(String fieldName) {
        return fieldErrors.getOrDefault(fieldName, List.of());
    }

    public boolean hasObjectError(String objectName) {
        return fieldErrors.keySet().stream()
                .anyMatch(key -> key.startsWith(objectName));
    }

}
