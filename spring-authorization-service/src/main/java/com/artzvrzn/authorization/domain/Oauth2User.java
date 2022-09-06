package com.artzvrzn.authorization.domain;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Oauth2User implements UserDetails {

  private final UUID id;
  private final String username;
  private final String firstName;
  private final String lastName;
  private final String password;
  private final Status status;
  private final String email;
  private final String phone;
  private final String country;
  private final String city;
  private final String streetName;
  private final String house;
  private final String apartment;
  private final Collection<? extends GrantedAuthority> authorities;

  public Oauth2User(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.password = user.getPassword();
    this.status = user.getStatus();
    this.email = user.getEmail();
    this.phone = user.getPhone();
    this.country = user.getCountry();
    this.city = user.getCity();
    this.streetName = user.getStreetName();
    this.house = user.getHouse();
    this.apartment = user.getApartment();
    this.authorities = user.getRoles()
        .stream()
        .map(r -> new SimpleGrantedAuthority(r.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
//    return Status.ACTIVE.equals(status);
    return true;
  }

  public UUID getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Status getStatus() {
    return status;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getStreetName() {
    return streetName;
  }

  public String getHouse() {
    return house;
  }

  public String getApartment() {
    return apartment;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
