package com.backgammon.v1.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {

  private String userName;

  private String email;

  private String password;

}
