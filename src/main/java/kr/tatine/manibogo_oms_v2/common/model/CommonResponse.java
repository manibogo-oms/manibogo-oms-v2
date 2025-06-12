package kr.tatine.manibogo_oms_v2.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse {

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
