package com.backgammon.v1.friendship.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyFriendShipRequestDto {

  private String friendName;

  private FriendShipStatus friendShipStatus;

}
