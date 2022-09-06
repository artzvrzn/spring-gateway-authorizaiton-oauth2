package com.artzvrzn.authorization.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "app")
public class User extends BaseEntity {
  @Id
  private UUID id;
  @Column(unique = true)
  private String username;
  private String firstName;
  private String lastName;
  private String password;
  @Enumerated(value = EnumType.STRING)
  private Status status;
  private String email;
  private String phone;
  private String country;
  private String city;
  private String streetName;
  private String house;
  private String apartment;
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "user_role",
      schema = "app",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles = new ArrayList<>();
}
