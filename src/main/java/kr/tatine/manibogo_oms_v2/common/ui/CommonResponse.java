package kr.tatine.manibogo_oms_v2.common.ui;

import kr.tatine.manibogo_oms_v2.common.model.Message;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommonResponse implements Serializable {

    private Message message;

    private ErrorResult errorResult = new ErrorResult();

    public CommonResponse(String messageCode, Object[] arguments, ErrorResult errorResult) {
        this.message = new Message(messageCode, arguments);
        this.errorResult = errorResult;
    }

    public CommonResponse(String messageCode, ErrorResult errorResult) {
        this.message = new Message(messageCode, new Object[]{});
        this.errorResult = errorResult;
    }
}
