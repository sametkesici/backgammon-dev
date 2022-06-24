package com.backgammon.v1.auth;

import com.backgammon.v1.security.jwt.BackGammonUserDetails;
import com.backgammon.v1.security.jwt.JwtResponse;
import com.backgammon.v1.security.jwt.JwtUtils;
import com.backgammon.v1.security.jwt.RefreshToken;
import com.backgammon.v1.security.jwt.RefreshTokenService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
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

  private final RefreshTokenService refreshTokenService;

  public JwtResponse authenticateUser(LoginRequestDto loginRequestDto){
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    BackGammonUserDetails userDetails = (BackGammonUserDetails) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return JwtResponse.builder().type("Bearer").id(userDetails.getId()).email(userDetails.getEmail()).username(
        userDetails.getUsername()).token(jwt).refreshToken(refreshToken.getToken()).roles(roles).build();
  }


}
