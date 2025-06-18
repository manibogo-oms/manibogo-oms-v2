package kr.tatine.manibogo_oms_v2.common.utils;

import kr.tatine.manibogo_oms_v2.common.model.PageControl;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    private PageUtils() {}

    public static PageControl initControl(Page<?> page) {

        int currentPage = page.getNumber();
        int totalPages = page.getTotalPages();

        final List<Integer> pageNumbers = new ArrayList<>();

        final int startPageNum = currentPage < 2
                ? 0
                : (Math.min(currentPage - 2, (totalPages - 5)));

        for (int p = startPageNum; p < (startPageNum + 5) && p < totalPages; p ++) {
            pageNumbers.add(p);
        }

        boolean showFirstNumber = currentPage > 2;
        boolean showLastNumber = currentPage < (totalPages - 3);

        return new PageControl(
            pageNumbers, showFirstNumber, showLastNumber, currentPage, totalPages, page.isFirst(), page.isLast(), page.getTotalElements());
    }

}
