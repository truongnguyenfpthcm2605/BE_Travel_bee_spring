package com.travelbee.app.config;


import com.travelbee.app.security.jwt.JwtEntryPoint;
import com.travelbee.app.security.jwt.JwtTokenFilter;
import com.travelbee.app.security.oauth2.OAuth2UserDetailService;
import com.travelbee.app.security.userprincal.UserDetailService;
import com.travelbee.app.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailService userDetailService;
    private final JwtTokenFilter jwtTokenFilter;
    private final OAuth2UserDetailService oAuth2UserDetailService;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    JwtEntryPoint getJwtEntryPoint() {
        return new JwtEntryPoint();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(cs -> cs.disable()).cors(cr -> cr.configurationSource(config -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(Collections.singletonList("*"));
                    configuration.setAllowedMethods(List.of("*"));
                    configuration.setAllowedMethods(Collections.singletonList("*"));
                    configuration.setExposedHeaders(Collections.singletonList("Authorization"));
                    return configuration;
                }))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET, "/api/v1/home/**").permitAll();
                    auth.requestMatchers("/api/v1/auth/**","/swagger-ui.html").permitAll();
                    auth.requestMatchers("/oauth2/authorization/**","/v3/api-docs/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/v1/hotel/**", "/api/v1/tour/**", "/api/v1/transport/**", "/api/v1/feedback","/api/v1/location/**").permitAll();
                    auth.requestMatchers("/api/v1/admin/**").hasAnyRole(Roles.ADMIN.name());
                    auth.requestMatchers("/api/v1/staff/**").hasAnyRole(Roles.STAFF.name());
                    auth.requestMatchers("/api/v1/feedback/**").authenticated();

                })
//                .formLogin(login ->
//                        login
//                                .loginProcessingUrl("/api/v1/auth/login")
//                                .defaultSuccessUrl("/api/v1/home")
//                                .usernameParameter("username")
//                                .passwordParameter("password")
//                                .loginPage("")
//                )
//                .rememberMe(rm -> {
//                    rm.rememberMeParameter("remember");
//                    rm.tokenValiditySeconds(86400);
//
//                })
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer.invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/auth/logout"))
                                .logoutSuccessUrl("/api/v1/auth/logout/success")
                )
                .oauth2Login(httpSecurityOAuth2LoginConfigurer ->
                        httpSecurityOAuth2LoginConfigurer
                                .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig.baseUri("/oauth2/authorization"))
                                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(oAuth2UserDetailService))
                                .defaultSuccessUrl("/api/v1/auth/oauth2")
                                .failureUrl("/api/v1/auth/login")
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/api/v1/auth/denied"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider());

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
