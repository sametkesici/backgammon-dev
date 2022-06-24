package com.backgammon.v1.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenRefreshResponse {

  private String accessToken;
  private String refreshToken;
  private String tokenType;

}
