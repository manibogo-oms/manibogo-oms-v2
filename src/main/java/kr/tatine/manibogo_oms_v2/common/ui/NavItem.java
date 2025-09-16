package kr.tatine.manibogo_oms_v2.common.ui;

import kr.tatine.manibogo_oms_v2.common.utils.UriUtils;
import org.springframework.data.domain.Page;

public record NavItem(
    String label, String url, boolean clickable, boolean isCurtPage) {

    public static NavItem number(Page<?> page, int number) {
        return new NavItem(
                String.valueOf(number),
                UriUtils.replacePage(number, page.getSize()),
                true,
                (page.getNumber() + 1 == number)
        );
    }

    public static NavItem dots() {
        return new NavItem("...", null, false, false);
    }


}
