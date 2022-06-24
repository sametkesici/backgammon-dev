package com.backgammon.v1.auth;

import com.backgammon.v1.security.jwt.JwtResponse;
import com.backgammon.v1.security.jwt.JwtUtils;
import com.backgammon.v1.security.jwt.RefreshToken;
import com.backgammon.v1.security.jwt.RefreshTokenService;
import com.backgammon.v1.security.jwt.TokenRefreshException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  private final RefreshTokenService refreshTokenService;

  private final JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    JwtResponse jwtResponse = authService.authenticateUser(loginRequestDto);
    return ResponseEntity.ok(jwtResponse);
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logOutUser(@Valid @RequestBody LogOutRequestDto logOutRequestDto){
    refreshTokenService.deleteByUserId(logOutRequestDto.getUserId());
    return ResponseEntity.ok("Log out Succesful!");
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody TokenRefreshRequestDto request){
    return refreshTokenService.findByToken(request.getRefreshToken())
                              .map(refreshTokenService::verifyExpiration)
                              .map(RefreshToken::getUser)
                              .map(user -> {
                                String token = jwtUtils.generateTokenFromUsername(user.getUserName());
                                return ResponseEntity.ok(new TokenRefreshResponse(token,request.getRefreshToken(),"Bearer"));
                              })
                              .orElseThrow(() -> new TokenRefreshException("Refresh token is not in database!"));
  }

}
