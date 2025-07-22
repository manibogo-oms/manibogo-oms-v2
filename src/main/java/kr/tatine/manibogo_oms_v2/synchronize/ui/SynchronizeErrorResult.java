package kr.tatine.manibogo_oms_v2.synchronize.ui;

import java.io.Serializable;
import java.util.List;

public record SynchronizeErrorResult(
        String itemOrderNumber,
        List<String> errorMessages) implements Serializable {
}
