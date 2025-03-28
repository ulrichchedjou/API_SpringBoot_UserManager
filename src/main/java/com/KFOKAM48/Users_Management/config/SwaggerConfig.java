package com.KFOKAM48.Users_Management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Users Management API")
                        .description("API for User Management")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("KFOKAM48")
                                .email("contact@example.com")));
    }
}
