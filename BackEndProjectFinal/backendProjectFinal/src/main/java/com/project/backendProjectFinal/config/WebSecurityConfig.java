package com.project.backendProjectFinal.config;

import com.project.backendProjectFinal.filters.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.Arrays;
import java.util.List;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    // Tất cả các endpoint đều yêu cầu xác thực mà không phân biệt role
                    requests
                            .requestMatchers(OPTIONS, "/**").permitAll() // Cho phép OPTIONS request
                            .requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix),
                                    String.format("%s/parking_lot",apiPrefix),
                                    String.format("%s/vehicle",apiPrefix),
                                    String.format("%s/parking_session", apiPrefix),
                                    String.format("%s/parking_session/checkin", apiPrefix),
                                    String.format("%s/parking_session/checkout", apiPrefix)



                            )
                            .permitAll()// Đăng ký và đăng nhập không yêu cầu xác thực

                            .requestMatchers(POST, String.format("%s/**", apiPrefix)).permitAll()
                            .requestMatchers(PUT, String.format("%s/**", apiPrefix)).permitAll()
                            .requestMatchers(DELETE, String.format("%s/**", apiPrefix)).permitAll()
                            .requestMatchers(GET, String.format("%s/**", apiPrefix)).permitAll()

                            .anyRequest().authenticated(); // Tất cả các request còn lại đều yêu cầu xác thực
                })
                .cors(Customizer.withDefaults());

        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("http://localhost:4200"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });
        return http.build();
    }
}
