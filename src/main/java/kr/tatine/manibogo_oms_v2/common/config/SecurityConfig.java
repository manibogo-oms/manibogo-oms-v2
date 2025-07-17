package kr.tatine.manibogo_oms_v2.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/v2/**").authenticated()
                .requestMatchers("/error", "/image/**", "/js/**", "/css/**").permitAll());

        http.formLogin(Customizer.withDefaults());

        return http.build();
    }

}
