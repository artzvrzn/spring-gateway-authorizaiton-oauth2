package com.artzvrzn.authorization.dto;

import com.artzvrzn.authorization.domain.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class UserDto {
  private UUID id;
  private String username;
  private String firstName;
  private String lastName;
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;
  @JsonIgnore
  private Status status;
  private String email;
  private String phone;
  private String country;
  private String city;
  private String street;
  private String house;
  private String apartment;
}
