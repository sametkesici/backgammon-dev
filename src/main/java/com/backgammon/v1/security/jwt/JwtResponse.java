package com.backgammon.v1.security.jwt;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

  private String token;

  private String type ;

  private String refreshToken;

  private Long id;

  private String username;

  private String email;

  private List<String> roles;

}
