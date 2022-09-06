package com.artzvrzn.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringResourceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringResourceApplication.class, args);
  }
}
