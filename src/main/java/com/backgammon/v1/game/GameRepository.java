package com.backgammon.v1.game;

import com.backgammon.v1.game.model.Game;
import com.backgammon.v1.game.model.GameStatus;
import com.backgammon.v1.user.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {

  Optional<Game> findByUserAndOpponentUserAndGameStatus(User user , User opponentUser, GameStatus gameStatus);

}
