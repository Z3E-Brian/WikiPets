package org.una.programmingIII.WikiPets.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.una.programmingIII.WikiPets.Service.JWTService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTService jwtService;

    @Autowired
    public SecurityConfig(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/graphql").permitAll();
//                    auth.requestMatchers("/graphiql").permitAll();
//                    auth.anyRequest().authenticated();
//                })
//              .addFilterBefore(new GraphQLRequestFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JWTAuthorizationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Deshabilitar CSRF si no es necesario
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll();  // Permitir todas las solicitudes sin autenticación
                });
        return http.build();
    }

}