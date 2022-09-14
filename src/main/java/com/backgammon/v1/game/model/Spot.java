package com.backgammon.v1.game.model;

import com.backgammon.v1.base.BaseEntity;
import java.util.List;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Spot extends BaseEntity {

  private PieceColor pieceColor;

  private Integer countOfPieces;

  public void increaseCountOfPieces(){
    countOfPieces++;
  }

  public void decreaseCountOfPieces(){
    countOfPieces--;
  }

}
