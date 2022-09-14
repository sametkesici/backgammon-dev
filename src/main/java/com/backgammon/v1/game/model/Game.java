package com.backgammon.v1.game.model;

import com.backgammon.v1.base.BaseEntity;
import com.backgammon.v1.user.model.User;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Game extends BaseEntity {

  @OneToOne
  private User user;

  @OneToOne
  private User opponentUser;

  @OneToOne
  private Board board;

  @OneToOne
  private User currentTurn;

  private GameStatus gameStatus;

  @OneToMany
  private List<Move> movesPlayed;

  //when bearingoff is true collect checkers
  private boolean bearingOff;

  @Column(nullable = false)
  private String url = UUID.randomUUID().toString();

}
