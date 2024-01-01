package com.elifintizam.FlightSearchApi.config;

/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity*/
public class SecurityConfig {

    /*
    * Authentication configurations handled in this class.
    *
    * I chose OAuth 2.0 Login for authentication.
    * Users can be authenticated with their GitHub account.
    *
    *
    * I left configurations as comment lines
    * to let API be usable by anyone who clones the project.
    *
    */


    /*@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/swagger-ui/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .build();

    }*/
}
