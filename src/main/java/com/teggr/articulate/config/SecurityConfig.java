package com.teggr.articulate.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final int REMEMBER_ME_VALIDITY_SECONDS = 30 * 24 * 60 * 60;

    @Bean
    UserDetailsService userDetailsService(
            @Value("${spring.security.user.name:articulate}") String username,
            @Value("${spring.security.user.password:change-me}") String password,
            @Value("${spring.security.user.roles:USER}") List<String> configuredRoles) {
        String[] roles = configuredRoles == null || configuredRoles.isEmpty()
                ? new String[]{"USER"}
                : configuredRoles.toArray(String[]::new);

        return new InMemoryUserDetailsManager(
                User.builder()
                        .username(username)
                        .password(asStoredPassword(password))
                        .roles(roles)
                        .build());
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            UserDetailsService userDetailsService,
            @Value("${articulate.security.remember-me.key:${SPRING_SECURITY_USER_PASSWORD:change-me}}")
            String rememberMeKey) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.GET, "/app.css", "/favicon.ico", "/**/*.css", "/**/*.js").permitAll()
                        .requestMatchers("/generate/**").authenticated()
                .requestMatchers("/articles/**").authenticated()
                        .anyRequest().authenticated())
                .requestCache(cache -> cache.requestCache(requestCache))
                .formLogin(form -> form.defaultSuccessUrl("/articles"))
                .rememberMe(rememberMe -> rememberMe
                        .key(rememberMeKey)
                        .tokenValiditySeconds(REMEMBER_ME_VALIDITY_SECONDS)
                        .userDetailsService(userDetailsService))
                .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }

    private static String asStoredPassword(String password) {
        if (password != null && password.startsWith("{")) {
            return password;
        }
        return "{noop}" + password;
    }
}
