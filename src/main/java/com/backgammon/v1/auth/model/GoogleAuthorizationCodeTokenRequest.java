package com.backgammon.v1.auth.model;

import com.backgammon.v1.auth.GoogleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleAuthorizationCodeTokenRequest {
    private final GoogleClient googleClient;
    private static String clientId = "858546635885-1pi5jt2qovfek5os8cemvb1prmbc23pe.apps.googleusercontent.com";
    private static String clientSecret = "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";
    private static String redirectUri = "http://localhost:8081/api/oauth2/google/login";

    public GoogleAuthorizationCodeTokenRequest(GoogleClient googleClient)
    {
        this.googleClient = googleClient;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    public AccessTokenResponse getAccessToken(String code) {
        return googleClient.getAccessToken(code, clientId, clientSecret, redirectUri, "authorization_code","");
    }

    public GoogleUserInfo getUserInfo(String accessToken){
        return googleClient.getUserInfo(accessToken);
    }

}