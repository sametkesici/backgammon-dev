package com.backgammon.v1.user.model;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

  private Long id;

  private String userName;

  private Set<Role> roles;

}
