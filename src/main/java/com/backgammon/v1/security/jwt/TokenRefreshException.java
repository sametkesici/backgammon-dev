package com.backgammon.v1.security.jwt;

import com.backgammon.v1.base.BaseException;

public class TokenRefreshException extends BaseException {

  public TokenRefreshException(String message, int httpStatus) {
    super(message, httpStatus);
  }

  public TokenRefreshException(String message) {
    super(message);
  }
}
