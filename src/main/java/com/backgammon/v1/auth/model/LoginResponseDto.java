package com.backgammon.v1.auth.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {

  private Long id;

  private String username;

  private String email;

  private List<String> roles;


}
