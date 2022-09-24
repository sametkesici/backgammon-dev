package com.backgammon.v1.game.model;

import static com.backgammon.v1.game.model.PieceColor.BLACK;
import static com.backgammon.v1.game.model.PieceColor.WHITE;

import com.backgammon.v1.base.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Board extends BaseEntity {

  @OneToMany
  private List<Spot> spots;

  public Board(){

    spots = new ArrayList<>(28);

    for(int i=0; i<28; i++) {
      spots.add(i,new Spot());
    }

    /* setting standard backgammon starting positions
     */
    spots.get(1).setPieceColor(WHITE);
    spots.get(1).setCountOfPieces(2);
    spots.get(6).setPieceColor(BLACK);
    spots.get(6).setCountOfPieces(5);
    spots.get(8).setPieceColor(BLACK);
    spots.get(8).setCountOfPieces(3);
    spots.get(12).setPieceColor(WHITE);
    spots.get(12).setCountOfPieces(5);
    spots.get(13).setPieceColor(BLACK);
    spots.get(13).setCountOfPieces(5);
    spots.get(17).setPieceColor(WHITE);
    spots.get(17).setCountOfPieces(3);
    spots.get(19).setPieceColor(WHITE);
    spots.get(19).setCountOfPieces(5);
    spots.get(24).setPieceColor(BLACK);
    spots.get(24).setCountOfPieces(2);

    /* we keep the eaten pills in [0] for white, and [25] for
     * black. also, we keep the pills that have exited the board
     * in [26] for white, and [27] for black
     */

    spots.get(0).setPieceColor(WHITE);
    spots.get(25).setPieceColor(BLACK);
    spots.get(26).setPieceColor(WHITE);
    spots.get(27).setPieceColor(BLACK);
  }

}
