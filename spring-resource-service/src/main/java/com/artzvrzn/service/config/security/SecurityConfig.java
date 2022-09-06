package com.artzvrzn.service.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .mvcMatcher("/**")
        .authorizeRequests()
        .mvcMatchers("/**")
        .access("hasAuthority('SCOPE_resource.read')")
        .and()
        .oauth2ResourceServer()
        .jwt();
    return http.build();
  }

}
