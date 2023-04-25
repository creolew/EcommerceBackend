package com.example.Ecommerce.config;

import com.example.Ecommerce.security.JwtAuthenticationEntryPoint;
import com.example.Ecommerce.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig  {

    private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    public WebSecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = jwtAuthenticationFilter;
    }



    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests( (authorize) ->
                                                    authorize
                                                    .requestMatchers("/api/auth/v1/**").permitAll()
                                                    .requestMatchers("/api/customer/v1/**").permitAll()
                                                    .requestMatchers("/api/order/v1/**").permitAll()
                                                    .requestMatchers("/api/orderDetail/v1/**").permitAll()

                                                            //.requestMatchers("/api/category/v1/**").hasAuthority("ROLE_EDITOR")
                                                    .anyRequest().authenticated()

                                )
                //.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint) )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) );



        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
