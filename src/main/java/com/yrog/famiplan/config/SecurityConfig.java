package com.yrog.famiplan.config;

import com.yrog.famiplan.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login","/css/**").permitAll()  // routes publiques
                        .anyRequest().authenticated()                  // tout le reste protégé
            )
            .formLogin(form -> form
                        .loginPage("/")                // page de login
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard") // redirection après connexion
                        .permitAll()
            )
            .logout(logout -> logout
                        .logoutSuccessUrl("/")         // redirection après déconnexion
            )
            .userDetailsService(userDetailsServiceImpl);
        return http.build();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
