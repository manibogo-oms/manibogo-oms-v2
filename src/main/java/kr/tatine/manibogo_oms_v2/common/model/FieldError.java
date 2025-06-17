package kr.tatine.manibogo_oms_v2.common.model;

import java.util.ArrayList;
import java.util.List;

public record FieldError(
        ErrorLevel level, List<Message> messages) {

    public FieldError addError(ErrorLevel level, Message message) {
        ArrayList<Message> prevMessages = new ArrayList<>(messages);
        prevMessages.add(message);

        return new FieldError(this.level.getHigherLevel(level), prevMessages.stream().toList());
    }

}