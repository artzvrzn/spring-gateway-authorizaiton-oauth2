package com.artzvrzn.authorization.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class SampleController {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Object get() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication;
  }
}
