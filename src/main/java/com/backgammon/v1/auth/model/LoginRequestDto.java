package com.backgammon.v1.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

  private String userName;

  private String password;

}
