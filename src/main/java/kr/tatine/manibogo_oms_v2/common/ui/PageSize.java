package kr.tatine.manibogo_oms_v2.common.ui;

import kr.tatine.manibogo_oms_v2.common.utils.UriUtils;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

public record PageSize (
   int size,
   String url,
   boolean isCurtSize
) {

    private static final int[] PAGE_SIZES = {
            10, 15, 20, 25, 50
    };

    public static List<PageSize> listOf(Page<?> page) {
        return Arrays.stream(PAGE_SIZES)
                .mapToObj(size -> createPageSize(page, size))
                .toList();
    }

    private static PageSize createPageSize(Page<?> page, int size) {
        return new PageSize(size, UriUtils.replacePage(page.getNumber(), size), size == page.getSize());
    }

}
