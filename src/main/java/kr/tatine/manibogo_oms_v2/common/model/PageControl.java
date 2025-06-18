package kr.tatine.manibogo_oms_v2.common.model;

import java.util.List;

public record PageControl(
        List<Integer> numbers,
        Boolean showFirstNumber,
        Boolean showLastNumber,
        Integer currentPage,
        Integer totalPage,
        Boolean isFirst,
        Boolean isLast,
        Long totalElements
) { }
