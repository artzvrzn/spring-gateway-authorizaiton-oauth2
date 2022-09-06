package com.artzvrzn.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringGatewayApplication.class, args);
  }
}
