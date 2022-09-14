package com.backgammon.v1.auth;

import com.backgammon.v1.auth.model.LoginRequestDto;
import com.backgammon.v1.auth.model.LoginResponseDto;
import com.backgammon.v1.auth.model.UserInfo;
import com.backgammon.v1.security.jwt.JwtUtils;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signin")
  public ResponseEntity<LoginResponseDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    UserInfo userInfo = authService.authenticateUser(loginRequestDto);

    LoginResponseDto loginResponseDto = LoginResponseDto.builder().id(userInfo.getId()).username(userInfo.getUsername()).email(
        userInfo.getEmail()).roles(userInfo.getRoles()).build();

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, userInfo.getJwtCookie()).body(loginResponseDto);
  }

  @PostMapping("/signout")
  public ResponseEntity<String> logoutUser(){
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, authService.clearJWTCookie())
                         .body("You've been signed out!");
  }

}
