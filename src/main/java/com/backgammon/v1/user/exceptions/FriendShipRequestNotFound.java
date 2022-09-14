package com.backgammon.v1.user.exceptions;

import com.backgammon.v1.base.BaseException;

public class FriendShipRequestNotFound extends BaseException {

  public FriendShipRequestNotFound(String message, int httpStatus) {
    super(message, httpStatus);
  }

  public FriendShipRequestNotFound(String message) {
    super(message);
  }
}
