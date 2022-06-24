package com.backgammon.v1.base;

import static com.backgammon.v1.base.BaseConstants.DEFAULT_ERROR_HTTP_STATUS;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {

  private final String message;

  private final int httpStatus;

  public BaseException(String message) {
    super(message);
    this.message = message;
    this.httpStatus = DEFAULT_ERROR_HTTP_STATUS;
  }

}
