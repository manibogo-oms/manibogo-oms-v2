package kr.tatine.manibogo_oms_v2.synchronize.ui;

import java.util.List;

public record SynchronizeErrorResult(
        String itemOrderNumber,
        List<String> errorMessages) {
}
