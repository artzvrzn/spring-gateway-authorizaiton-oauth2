package com.artzvrzn.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringAuthorizationApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAuthorizationApplication.class, args);
  }
}
