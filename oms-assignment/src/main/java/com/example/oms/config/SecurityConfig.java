package com.example.oms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails manager = User.withUsername("manager")
                .password("{noop}manager123")
                .roles("MANAGER")
                .build();

        UserDetails operator = User.withUsername("operator")
                .password("{noop}operator123")
                .roles("OPERATOR")
                .build();

        return new InMemoryUserDetailsManager(admin, manager, operator);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/", "/index.html", "/app.js", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/sample/**").permitAll() // ✅ Allow sample CSV download without login

                // Protected endpoints
                .requestMatchers(HttpMethod.POST, "/api/orders/upload").hasAnyRole("ADMIN","OPERATOR")
                .requestMatchers(HttpMethod.POST, "/api/customers/upload").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/products/upload").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/orders").hasAnyRole("ADMIN","OPERATOR")
                .requestMatchers(HttpMethod.PUT, "/api/orders/**").hasAnyRole("ADMIN","OPERATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/orders/**").hasAnyRole("ADMIN","MANAGER","OPERATOR")
                .requestMatchers(HttpMethod.GET, "/api/customers/**").hasAnyRole("ADMIN","MANAGER","OPERATOR")
                .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyRole("ADMIN","MANAGER","OPERATOR")

                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
