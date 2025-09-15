package kr.tatine.manibogo_oms_v2.common.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UriUtils {

    private UriUtils() {}

    public static String replacePage(int page, int size) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam("page", page)
                .replaceQueryParam("size", size)
                .build().toUriString();
    }
}
