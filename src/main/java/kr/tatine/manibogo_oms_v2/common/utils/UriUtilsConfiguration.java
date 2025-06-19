package kr.tatine.manibogo_oms_v2.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class UriUtilsConfiguration {

    @Bean
    public Supplier<String> getCurrentPath() {
        return () -> getHttpServletRequest()
                .getServletPath();
    }

    @Bean
    public Supplier<String> getCurrentQuery() {
        return () -> getHttpServletRequest()
                .getQueryString();
    }

    @Bean
    public BiFunction<String, String, String> replaceOrAddParam() {
        return (paramName, newValue) -> ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replaceQueryParam(paramName, newValue)
                .build().toUriString();
    }

    @Bean
    public Function<String, String> replaceUri() {
        return (newPath) -> {
            final URI newUri = URI.create(newPath);

            final ServletUriComponentsBuilder uriBuilder =
                    ServletUriComponentsBuilder.fromCurrentRequest();

            if (newUri.getPath() != null) {
                uriBuilder.replacePath(newUri.getPath());
            }

            final String query = newUri.getQuery();

            final Map<String, String> params = parseQueryParams(query);

            for (Entry<String, String> paramEntry : params.entrySet()) {
                uriBuilder.replaceQueryParam(paramEntry.getKey(), paramEntry.getValue());
            }

            return uriBuilder.build().toUriString();
        };
    }

    private Map<String, String> parseQueryParams(String query) {
        if (query == null) return Map.of();

        final Map<String, String> params = new HashMap<>();

        final String[] pairs = query.split("&");

        for (String pair : pairs) {
            String[] entry = pair.split("=");

            if (entry.length != 2) continue;
            params.put(entry[0], entry[1]);
        }
        return params;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
    }

}
