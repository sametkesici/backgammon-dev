package com.backgammon.v1.user.exceptions;

import com.backgammon.v1.base.BaseException;

public class UserNotFoundException extends BaseException {

  public UserNotFoundException(String message, int httpStatus) {
    super(message, httpStatus);
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
