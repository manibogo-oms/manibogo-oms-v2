package kr.tatine.manibogo_oms_v2.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

public class RedirectionUtils {

    private RedirectionUtils() {}

    public static String getUrlRemainQuery(String redirectPath) {
        final String queryString = getCurrentServletRequest().getQueryString();

        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(redirectPath);

        if (queryString != null && !queryString.isEmpty()) {
            uriBuilder.query(queryString);
        }

        return uriBuilder.toUriString();
    }

    private static HttpServletRequest getCurrentServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }


}
