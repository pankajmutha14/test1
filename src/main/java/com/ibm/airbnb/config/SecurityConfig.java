package com.ibm.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private JWTFilterRequest jwtFilterRequest;

    public SecurityConfig(JWTFilterRequest jwtFilterRequest) {
        this.jwtFilterRequest = jwtFilterRequest;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtFilterRequest, AuthorizationFilter.class);

        http.authorizeHttpRequests().anyRequest().permitAll();
//                .requestMatchers("/api/v1/users/verifyLogin", "/api/v1/users/addUser").permitAll()
//                .requestMatchers(HttpMethod.GET,"/api/v1/property/byLocation/{locationName}").permitAll()
//                .requestMatchers("/api/v1/countries/addCountry","/api/v1/users/logout").hasRole("ADMIN")
//                .requestMatchers("/api/v1/bookings/book","/api/v1/users/logout").hasAnyRole("USER","ADMIN")
//                .requestMatchers("/api/v1/reviews/addReview").hasAnyRole("USER","ADMIN")
//                .anyRequest().authenticated();

        return http.build();
    }
}
