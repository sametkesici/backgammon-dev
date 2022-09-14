package com.backgammon.v1.game;

import com.backgammon.v1.game.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {}
