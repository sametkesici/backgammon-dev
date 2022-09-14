package com.backgammon.v1.game.model;

import com.backgammon.v1.base.BaseEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Piece extends BaseEntity {

  private boolean killed = false;

  private boolean moveValid(Board board , Spot start , Spot end){
    //TODO moveValid -> Service
    return true;
  }

}
