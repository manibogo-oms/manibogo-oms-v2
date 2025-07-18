package kr.tatine.manibogo_oms_v2.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RedisTemplate<String, String> redisTemplate) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/v2/**").authenticated()
                .requestMatchers("/error", "/image/**", "/js/**", "/css/**").permitAll());

        http.formLogin(Customizer.withDefaults());

        http.rememberMe(rmc -> rmc.tokenRepository(persistentTokenRepository(redisTemplate)));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(RedisTemplate<String, String> redisTemplate) {
        return new RedisPersistentTokenRepository(redisTemplate);
    }

}
