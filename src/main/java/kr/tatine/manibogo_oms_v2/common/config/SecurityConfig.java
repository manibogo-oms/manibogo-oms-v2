package kr.tatine.manibogo_oms_v2.common.config;

import kr.tatine.manibogo_oms_v2.member.query.MemberDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class SecurityConfig {

    @Value("${security.remember-me.key}")
    private String rememberMeKey;

    @Value("${security.remember-me.ttl}")
    private Integer rememberMeTTL;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RememberMeServices rememberMeServices) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/v2/orders", "/v2/orders/**", "/v2/products", "/v2/products/**", "/v2/synchronize/**", "/v2/members", "/v2/members/**").hasRole("ADMIN")
                .requestMatchers("/v2/logistics", "/v2/logistics/**").hasAnyRole("ADMIN", "LOGISTICS")
                .requestMatchers("/error", "/image/**", "/js/**", "/css/**").permitAll())
                .formLogin(Customizer.withDefaults())
                .rememberMe(rmc -> rmc.rememberMeServices(rememberMeServices));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(RedisTemplate<String, String> redisTemplate) {
        return new RedisPersistentTokenRepository(redisTemplate, rememberMeTTL);
    }

    @Bean
    public UserDetailsService userDetailsService(MemberDao memberDao) {
        return new MemberUserDetailsService(memberDao);
    }

    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService, PersistentTokenRepository persistentTokenRepository) {
        final PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices(rememberMeKey, userDetailsService, persistentTokenRepository);

        rememberMeServices.setTokenValiditySeconds(rememberMeTTL);

        return rememberMeServices;
    }


}
