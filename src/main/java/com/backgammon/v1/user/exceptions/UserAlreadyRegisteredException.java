package com.backgammon.v1.user.exceptions;

import com.backgammon.v1.base.BaseException;

public class UserAlreadyRegisteredException extends BaseException {

  public UserAlreadyRegisteredException(String message, int httpStatus) {
    super(message, httpStatus);
  }

  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
