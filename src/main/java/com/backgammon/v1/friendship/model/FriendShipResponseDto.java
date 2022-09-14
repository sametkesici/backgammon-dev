package com.backgammon.v1.friendship.model;

import com.backgammon.v1.user.model.UserDto;
import lombok.Data;

@Data
public class FriendShipResponseDto {

  private UserDto user;

  private UserDto friend;

  private FriendShipStatus status;

}
