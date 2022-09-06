package com.artzvrzn.authorization.config.security;

import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;

@Configuration
public class AuthorizationServerConfig {

  @Bean
  public RegisteredClientRepository registeredClientRepository() {
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("gateway-client-id")
        .clientSecret("$2a$10$M2IZJ7jcz2Mdlcve8PitoO5tPWBqpo7jHddc7mTkiRn8xLb3RmoWS")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .redirectUri("http://spring-gateway:8080/login/oauth2/code/gateway-client")
        .redirectUri("http://spring-gateway:8080/authorized")
        .scope(OidcScopes.OPENID)
        .scope("resource.read")
        .scope("resource.write")
        .build();
    return new InMemoryRegisteredClientRepository(registeredClient);
  }

  @Bean
  public ProviderSettings providerSettings() {
    return ProviderSettings.builder()
        .issuer("http://auth-service:9000")
        .build();
  }
}
