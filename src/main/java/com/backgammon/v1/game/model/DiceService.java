package com.backgammon.v1.game.model;

import static java.lang.Boolean.FALSE;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DiceService {

  private final Random rand = new SecureRandom();

  public Die rollOneDie(){
    return Die.builder().dieValue(randomNumber()).build();
  }

  public Dice rollDice(){
    List<Die> dieList = new ArrayList<>();

    Die firstDie = rollOneDie();
    Die secondDie = rollOneDie();
    dieList.add(firstDie);
    dieList.add(secondDie);

    log.info("first die : " + dieList.get(0) +"second die : " + dieList.get(1));
    return Dice.builder().dice(dieList).isDouble(isDoubles(dieList)).build();
  }

  public boolean isDoubles(List<Die> dice) {
    return dice.get(0).equals(dice.get(1));
  }

  public Integer randomNumber(){
    Integer randomNumber = rand.nextInt(6)  + 1;
    log.info("die " + randomNumber);
    return randomNumber;
  }

}

