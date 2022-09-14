package com.backgammon.v1.game.model;

import com.backgammon.v1.base.BaseEntity;
import com.backgammon.v1.user.model.User;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Move extends BaseEntity {

  @OneToOne
  private User user;

  @OneToOne
  private Spot start;

  @OneToOne
  private Spot end;

  @OneToOne
  private Piece piece;

}
