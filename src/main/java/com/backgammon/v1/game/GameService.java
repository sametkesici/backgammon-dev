package com.backgammon.v1.game;

import com.backgammon.v1.auth.AuthService;
import com.backgammon.v1.game.exception.PendingGameNotFoundException;
import com.backgammon.v1.game.model.Board;
import com.backgammon.v1.game.model.Game;
import com.backgammon.v1.game.model.GameStatus;
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

  private final BoardService boardService;

  public String inviteToGame(String opponentUsername) {
    User user = userService.findUserByUserName(authService.currentUsername());
    User opponentUser = userService.findUserByUserName(opponentUsername);

    Game game = new Game();
    game.setGameStatus(GameStatus.PENDING);
    game.setUser(user);
    game.setOpponentUser(opponentUser);

    gameRepository.save(game);

    return game.getUrl();
  }

  public String replyInvite(ReplyGameInviteRequest replyGameInviteRequest) {
    if (replyGameInviteRequest.getGameStatus().equals(GameStatus.REJECTED)) {
      return null;
    }

    User user = userService.findUserByUserName(authService.currentUsername());

    User opponentUser = userService.findUserByUserName(replyGameInviteRequest.getOpponentUsername());

    Game game = gameRepository
        .findByUserAndOpponentUserAndGameStatus(opponentUser, user, GameStatus.PENDING)
        .orElseThrow(() -> new PendingGameNotFoundException("there is no pending game"));

    startGame(game);

    return game.getUrl();
  }

  private Game startGame(Game game) {

    Board board = new Board();

    game.setBoard(board);
    game.setGameStatus(GameStatus.ACTIVE);
    //TODO add spot to database
    boardService.saveBoard(board);

    return gameRepository.save(game);
  }


}
