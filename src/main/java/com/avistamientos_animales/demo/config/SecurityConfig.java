package com.avistamientos_animales.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.avistamientos_animales.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // permite POST en formularios Thymeleaf
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/login", "/observadores/guardar", "/observadores/crear-cuenta", "/style/**")
                    .permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/observador/**").hasRole("OBSERVADOR")
                    .anyRequest().authenticated()
            )
            .userDetailsService(userDetailsService)
            .formLogin(form -> form
                    .loginPage("/login")
                    .successHandler((request, response, authentication) -> {
                        var roles = authentication.getAuthorities();

                        if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
                            response.sendRedirect("/admin/home");
                        } else {    
                            response.sendRedirect("/observador/home");
                        }
                    })
                    .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}