package kr.tatine.manibogo_oms_v2.synchronize.ui;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class SynchronizeResponse implements Serializable {

    private List<SynchronizeResult> successes = new ArrayList<>();
    private List<SynchronizeResult> skips = new ArrayList<>();
    private List<SynchronizeErrorResult> errors = new ArrayList<>();

    private List<String> globalErrors = new ArrayList<>();

    public void success(SynchronizeResult result) {
        this.successes.add(result);
    }

    public void skip(SynchronizeResult result) {
        this.skips.add(result);
    }

    public void error(SynchronizeErrorResult result) {
        this.errors.add(result);
    }

    public void globalError(String message) {
        this.globalErrors.add(message);
    }

}
