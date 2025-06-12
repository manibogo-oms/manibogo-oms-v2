package kr.tatine.manibogo_oms_v2.common.model;

import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ErrorResult {

    private final MultiValueMap<String, ErrorMessage> fieldErrors = new LinkedMultiValueMap<>();

    private final List<ErrorMessage> globalErrors = new ArrayList<>();

    public void rejectValue(String fieldName, String errorCode, Object[] arguments) {
        fieldErrors.add(fieldName, new ErrorMessage(errorCode, arguments));
    }

    public void rejectValue(String fieldName, String errorCode) {
        rejectValue(fieldName, errorCode, new Object[0]);
    }

    public void reject(String errorCode, Object[] arguments) {
        globalErrors.add(new ErrorMessage(errorCode, arguments));
    }

    public void reject(String errorCode) {
        reject(errorCode, new Object[0]);
    }

    public boolean hasGlobalError() {
        return !globalErrors.isEmpty();
    }

    public List<ErrorMessage> getFieldErrors(String fieldName) {
        return fieldErrors.getOrDefault(fieldName, List.of());
    }

}
