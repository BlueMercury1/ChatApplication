package com.socialsync.socialsync.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomShopkeeperDetaillsService customShopkeeperDetaillsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(http -> {
                    http.requestMatchers("/api/**").authenticated()
                            .anyRequest().permitAll();
                })
                // .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                // .sessionManagement(session -> {
                // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                // })
                .csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        System.err.println("\n2nd-HIT for AuthenticationManager's provider\n");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customShopkeeperDetaillsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        System.err.println("\n1st-HIT for authenticaton Manager\n");
        return authenticationConfiguration.getAuthenticationManager();
    }

}
