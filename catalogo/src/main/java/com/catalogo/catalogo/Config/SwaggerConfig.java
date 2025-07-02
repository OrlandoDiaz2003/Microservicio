package com.catalogo.catalogo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

// swagger url http://localhost:8080/doc/swagger-ui/index.html#/
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 2025 catálogo")
                        .version("1.0.3")
                        .description("Documentación de la API para manejar productos del catálogo"));
    }

}

