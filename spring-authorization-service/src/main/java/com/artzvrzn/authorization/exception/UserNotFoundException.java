package com.artzvrzn.authorization.exception;

public class UserNotFoundException extends RuntimeException {

  private static final String BASE_MESSAGE = "User not found";

  public UserNotFoundException() {
    super(BASE_MESSAGE);
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
