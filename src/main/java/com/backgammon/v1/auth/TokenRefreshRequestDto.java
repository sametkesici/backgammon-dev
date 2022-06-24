package com.backgammon.v1.auth;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequestDto {

  @NotBlank
  private String refreshToken;

}
