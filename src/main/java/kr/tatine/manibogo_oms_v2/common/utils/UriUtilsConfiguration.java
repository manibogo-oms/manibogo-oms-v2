package kr.tatine.manibogo_oms_v2.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class UriUtilsConfiguration {

    @Bean
    public Supplier<String> getCurrentPath() {
        return () -> ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest()
                .getServletPath();
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

            if (newUri.getQuery() != null) {
                uriBuilder.query(newUri.getQuery());
            }

            return uriBuilder.build().toUriString();
        };
    }

}
