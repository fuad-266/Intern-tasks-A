package com.oauth.securityOauth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityconfig {
    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http){
return http.build();

}}

