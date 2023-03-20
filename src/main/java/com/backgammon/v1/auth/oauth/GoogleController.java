package com.backgammon.v1.auth.oauth;

import com.backgammon.v1.auth.model.AccessTokenResponse;
import com.backgammon.v1.auth.model.GoogleAuthorizationCodeTokenRequest;
import com.backgammon.v1.auth.model.GoogleUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;

@Controller
@RequestMapping("/api/oauth2/google")
@RequiredArgsConstructor
public class GoogleController {

    private final GoogleAuthorizationCodeTokenRequest googleAuthorizationCodeTokenRequest;


    @GetMapping("/login")
        public ResponseEntity<Object> login(@RequestParam("code") String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:3000"));

        AccessTokenResponse accessTokenResponse = googleAuthorizationCodeTokenRequest.getAccessToken(code);
        String accessToken = "Bearer " + accessTokenResponse.getAccessToken();

        GoogleUserInfo googleUserInfo = googleAuthorizationCodeTokenRequest.getUserInfo(accessToken);


        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
