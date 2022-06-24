package com.backgammon.v1.base;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvicer {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<Response<String>> handleBaseException(BaseException baseException){
    Response<String> response = Response.fromPayload(baseException.getClass().getSimpleName(),baseException.getMessage());
    return new ResponseEntity<>(response,new HttpHeaders(),baseException.getHttpStatus());
  }

}
