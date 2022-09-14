package com.backgammon.v1.game;

import com.backgammon.v1.auth.AuthService;
import com.backgammon.v1.game.model.Board;
import com.backgammon.v1.game.model.Game;
import com.backgammon.v1.game.model.GameStatus;
import com.backgammon.v1.game.model.request.InviteGameRequestDto;
import com.backgammon.v1.game.model.request.ReplyGameInviteRequest;
import com.backgammon.v1.user.UserService;
import com.backgammon.v1.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

  private final GameRepository gameRepository;

  private final AuthService authService;

  private final UserService userService;

  public void inviteToGame(InviteGameRequestDto inviteGameRequestDto) {
    User user = userService.findUserByUserName(authService.currentUsername());
    User opponentUser = userService.findUserByUserName(inviteGameRequestDto.getOpponentUsername());

  }

  public String replyInvite(ReplyGameInviteRequest replyGameInviteRequest) {
    if (replyGameInviteRequest.getGameStatus().equals(GameStatus.REJECTED)) {
      return null;
    }
    User user = userService.findUserByUserName(authService.currentUsername());

    User opponentUser = userService.findUserByUserName(replyGameInviteRequest.getOpponentUsername());

    Game game = startGame(user, opponentUser);

    return game.getUrl();
  }

  private Game startGame(User user, User opponentUser) {

    Board board = new Board();

    Game game = Game
        .builder()
        .gameStatus(GameStatus.ACTIVE)
        .user(user)
        .opponentUser(opponentUser)
        .bearingOff(false)
        .board(board)
        .build();

    return gameRepository.save(game);
  }
}
