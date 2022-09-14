package com.backgammon.v1.base.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {
    final String apiTitle = String.format("%s API", StringUtils.capitalize("Backgammon"));
    return new OpenAPI().info(new Info().title(apiTitle).version("v1"));
  }
}
