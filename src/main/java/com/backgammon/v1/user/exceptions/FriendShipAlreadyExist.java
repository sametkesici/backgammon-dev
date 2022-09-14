package com.backgammon.v1.user.exceptions;

import com.backgammon.v1.base.BaseException;

public class FriendShipAlreadyExist extends BaseException {

  public FriendShipAlreadyExist(String message, int httpStatus) {
    super(message, httpStatus);
  }

  public FriendShipAlreadyExist(String message) {
    super(message);
  }
}
