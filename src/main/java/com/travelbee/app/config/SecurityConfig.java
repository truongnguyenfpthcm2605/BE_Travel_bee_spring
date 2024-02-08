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
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
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
        http.csrf(AbstractHttpConfigurer::disable).cors(cr -> cr.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/home/**","/ap1/v1/health").permitAll();
                    auth.requestMatchers("/api/v1/auth/**").permitAll();
                    auth.requestMatchers("/oauth2/authorization/**",
                            "https://accounts.google.com/o/oauth2/v2/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/v1/hotel/**", "/api/v1/tour/**",
                            "api/v1/comment/**", "/api/v1/transport/**", "/api/v1/feedback", "/api/v1/location/**","/api/v1/like/**").permitAll();
                    auth.requestMatchers("/api/v1/admin/**").hasAuthority(Roles.ADMIN.name());
                    auth.requestMatchers("/api/v1/staff/**").hasAnyAuthority(Roles.STAFF.name(), Roles.ADMIN.name());
                    auth.requestMatchers(HttpMethod.POST, "/api/v1/payment/save").permitAll();
                    auth.anyRequest().authenticated();

                })
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
                                .failureUrl("/api/v1/auth/fail")
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/api/v1/auth/denied"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider());

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
        configuration.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
