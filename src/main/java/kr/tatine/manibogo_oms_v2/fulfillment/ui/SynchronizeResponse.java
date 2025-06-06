package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class SynchronizeResponse {

    private List<SynchronizeResult> successes = new ArrayList<>();
    private List<SynchronizeResult> skips = new ArrayList<>();
    private List<SynchronizeErrorResult> errors = new ArrayList<>();

    public void success(SynchronizeResult result) {
        this.successes.add(result);
    }

    public void skip(SynchronizeResult result) {
        this.skips.add(result);
    }

    public void error(SynchronizeErrorResult result) {
        this.errors.add(result);
    }

}
