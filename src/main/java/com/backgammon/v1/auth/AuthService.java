package com.backgammon.v1.auth;

import com.backgammon.v1.auth.model.LoginRequestDto;
import com.backgammon.v1.auth.model.UserInfo;
import com.backgammon.v1.user.socket.OnlineUsersSocket;
import com.backgammon.v1.security.BackGammonUserDetails;
import com.backgammon.v1.security.jwt.JwtUtils;
import com.backgammon.v1.user.model.RoleType;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtUtils jwtUtils;

  private final OnlineUsersSocket onlineUserSocketSocket;

  public UserInfo authenticateUser(LoginRequestDto loginRequestDto) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginRequestDto.getUserName(),
        loginRequestDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    BackGammonUserDetails userDetails = (BackGammonUserDetails) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails
        .getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    return UserInfo
        .builder()
        .id(userDetails.getId())
        .username(userDetails.getUsername())
        .email(userDetails.getEmail())
        .roles(roles)
        .jwtCookie(jwtCookie.toString())
        .build();
  }

  public String currentUsername() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }

  public Boolean isAuth() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    List<String> authorities = auth.getAuthorities().stream()
                                   .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    if (authorities.contains(RoleType.USER.toString())) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  public String clearJWTCookie() {
    return jwtUtils.getCleanJwtCookie().toString();
  }
}
