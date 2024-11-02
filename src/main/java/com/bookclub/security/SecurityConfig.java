package com.bookclub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 1. Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 2. SecurityFilterChain Bean using the lambda syntax
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Restrict access to /monthly-books URLs to only ADMIN users
                .requestMatchers("/monthly-books", "/monthly-books/list", "/monthly-books/new").hasRole("ADMIN")
                // Require authentication for all other requests
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login").defaultSuccessUrl("/", true) // Custom login page
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true)
                .permitAll()
            );
        return http.build();
    }

    // 3. In-Memory User Details (Replaces configure(AuthenticationManagerBuilder auth))
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();

        // Creating users with roles
        UserDetails user = User.builder()
            .username("user")
            .password(encoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(encoder.encode("admin123"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
