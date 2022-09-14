package com.backgammon.v1.game.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Dice {

  private List<Die> dice;

  private Boolean isDouble;

}
