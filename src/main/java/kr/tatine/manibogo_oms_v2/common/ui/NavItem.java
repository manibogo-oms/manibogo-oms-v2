package kr.tatine.manibogo_oms_v2.common.ui;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public record NavItem(
    String label, String url, boolean clickable, boolean isCurtPage) {

    public static NavItem number(Page<?> page, int number) {
        return new NavItem(
                String.valueOf(number),
                buildUrl(number, page.getSize()),
                true,
                (page.getNumber() == number)
        );
    }

    public static NavItem dots() {
        return new NavItem("...", null, false, false);
    }

    private static String buildUrl(int page, int size) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam("page", page)
                .replaceQueryParam("size", size)
                .build().toUriString();
    }

}
