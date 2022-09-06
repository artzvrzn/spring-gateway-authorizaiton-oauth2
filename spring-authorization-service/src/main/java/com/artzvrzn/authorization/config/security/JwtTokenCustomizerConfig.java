package com.artzvrzn.authorization.config.security;

import com.artzvrzn.authorization.domain.Oauth2User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

@Configuration
public class JwtTokenCustomizerConfig {

  @Bean
  public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
    return (context) -> {
      if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
        Oauth2User user = (Oauth2User) context.getPrincipal().getPrincipal();
        context.getClaims().claims((claims) -> {
          claims.put("id", user.getId().toString());
          claims.put("username", user.getUsername());
          claims.put("firstName", user.getFirstName());
          claims.put("lastName", user.getLastName());
          claims.put("status", user.getStatus());
          claims.put("email", user.getEmail());
          claims.put("phone", user.getPhone());
          claims.put("country", user.getCountry());
          claims.put("city", user.getCity());
          claims.put("street", user.getStreetName());
          claims.put("apartment", user.getApartment());
        });
      }
    };
  }
}
