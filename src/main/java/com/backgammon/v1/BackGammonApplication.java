package com.backgammon.v1;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BackGammonApplication {

  @Value("${rt-server.host}")
  private String host;

  @Value("${rt-server.port}")
  private Integer port;

  @Bean
  public SocketIOServer socketIOServer() {
    Configuration config = new Configuration();
    config.setHostname(host);
    config.setPort(port);
    return new SocketIOServer(config);
  }

  public static void main(String[] args){
    SpringApplication.run(BackGammonApplication.class,args);
  }
}
