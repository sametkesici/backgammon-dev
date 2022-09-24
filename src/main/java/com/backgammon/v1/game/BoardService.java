package com.backgammon.v1.game;

import com.backgammon.v1.game.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  @Transactional
  public void saveBoard(Board board){
    boardRepository.save(board);
  }

}
