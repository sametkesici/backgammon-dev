package com.backgammon.v1.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

  private String message;

  private T payload;

  public static <T> Response<T> fromPayload(T payload) {
    Response<T> response = new Response<>();
    response.setPayload(payload);
    return response;
  }

  public static <T> Response<T> fromPayload(T payload , String message) {
    Response<T> response = new Response<>();
    response.setMessage(message);
    response.setPayload(payload);
    return response;
  }

}
