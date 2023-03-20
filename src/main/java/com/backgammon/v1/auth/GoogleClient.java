package com.backgammon.v1.auth;

import com.backgammon.v1.auth.model.AccessTokenResponse;
import com.backgammon.v1.auth.model.GoogleUserInfo;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "googleOAuthClient", url = "https://www.googleapis.com/oauth2")
public interface GoogleClient {

    @PostMapping("/v3/token")
    @Headers("Content-Type: application/json")
    AccessTokenResponse getAccessToken(@RequestParam("code") String code,
                                       @RequestParam("client_id") String clientId,
                                       @RequestParam("client_secret") String clientSecret,
                                       @RequestParam("redirect_uri") String redirectUri,
                                       @RequestParam("grant_type") String grantType,
                                       @RequestBody String cort);

    @PostMapping("/token")
    AccessTokenResponse refreshToken(@RequestParam("client_id") String clientId,
                                     @RequestParam("client_secret") String clientSecret,
                                     @RequestParam("refresh_token") String refreshToken,
                                     @RequestParam("grant_type") String grantType);


    @GetMapping("/v2/userinfo?fields=name,email,picture")
    GoogleUserInfo getUserInfo(@RequestHeader("Authorization") String authorizationHeader);


}
