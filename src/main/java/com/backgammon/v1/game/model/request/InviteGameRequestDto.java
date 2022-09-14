package com.backgammon.v1.game.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class InviteGameRequestDto {

  private String opponentUsername;


}
