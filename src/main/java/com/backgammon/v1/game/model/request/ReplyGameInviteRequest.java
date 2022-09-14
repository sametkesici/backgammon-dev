package com.backgammon.v1.game.model.request;

import com.backgammon.v1.game.model.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReplyGameInviteRequest {

  private String opponentUsername;

  private GameStatus gameStatus;

}
