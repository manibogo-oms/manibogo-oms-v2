package kr.tatine.manibogo_oms_v2.common.ui;

import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;

public record PageView<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious,
        List<NavItem> navItems,
        List<PageSize> pageSizes
) {

    public static <T> PageView<T> of(Page<T> page) {

        return new PageView<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious(),
                buildNavItems(page),
                PageSize.listOf(page)
        );
    }

    private static List<NavItem> buildNavItems(Page<?> page) {

        final List<NavItem> navItems = new ArrayList<>();

        final int pageNumber = page.getNumber() + 1;

        final int totalPages = page.getTotalPages();

        final int startPage = Math.max(1, Math.min(pageNumber - 2, totalPages - 4));
        final int endPage = Math.min(startPage + 4, totalPages);


        if (startPage > 1) {
            navItems.add(NavItem.number(page, 1));
        }

        if (startPage > 2) {
            navItems.add(NavItem.dots());
        }

        for (int i = startPage; i <= endPage; i ++) {
            navItems.add(NavItem.number(page, i));
        }

        if (endPage < (totalPages - 1)) {
            navItems.add(NavItem.dots());
        }

        if (endPage < totalPages) {
            navItems.add(NavItem.number(page, totalPages));
        }

        return navItems;
    }

}
