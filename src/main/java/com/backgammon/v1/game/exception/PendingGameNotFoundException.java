package com.backgammon.v1.game.exception;

import com.backgammon.v1.base.BaseException;

public class PendingGameNotFoundException extends BaseException {

  public PendingGameNotFoundException(String message) {
    super(message);
  }
}
