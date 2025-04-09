package com.project.backendProjectFinal.config;

import com.project.backendProjectFinal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration

@RequiredArgsConstructor
public class SercurityConfig {
    private final UserRepository userRepository;

    // Cấu hình UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return phoneNumber -> userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with phone number = " + phoneNumber));
    }

    // Bỏ qua PasswordEncoder vì không mã hóa mật khẩu
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        // Không sử dụng PasswordEncoder vì không cần mã hóa mật khẩu
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // Dùng NoOpPasswordEncoder để bỏ qua mã hóa
        return authProvider;
    }

    // Cấu hình AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
