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
        List<NavItem> navItems
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
                buildNavItems(page)
        );
    }

    private static List<NavItem> buildNavItems(Page<?> page) {

        final List<NavItem> navItems = new ArrayList<>();

        final int pageNumber = page.getNumber();

        if (pageNumber > 3) {
            navItems.add(NavItem.number(page, 0));
        }

        if (pageNumber > 4) {
            navItems.add(NavItem.dots());
        }

        final int totalPages = page.getTotalPages();

        final int startPage = pageNumber < 2 ? 0 : Math.min(pageNumber - 2, totalPages - 5);

        for (int i = startPage; i < Math.min(startPage + 5, totalPages); i ++) {
            navItems.add(NavItem.number(page, i));
        }

        if (pageNumber < (totalPages - 4)) {
            navItems.add(NavItem.dots());
        }

        if (pageNumber < (totalPages - 3)) {
            navItems.add(NavItem.number(page, totalPages - 1));
        }

        return navItems;
    }

}
