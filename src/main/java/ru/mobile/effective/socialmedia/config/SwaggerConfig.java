package ru.mobile.effective.socialmedia.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
        (
                info = @io.swagger.v3.oas.annotations.info.Info
                        (
                                title = "Social Media API",
                                description = "Social Media",
                                version = "1.0.0",
                                contact = @Contact(name = "Kovtunov Vladimir",
                                        email = "kovtynov.vladimir@gmail.com")
                        )
        )
@SecurityScheme
        (
                name = "JWT",
                type = SecuritySchemeType.HTTP,
                description = "JWT auth description",
                bearerFormat = "JWT",
                scheme = "bearer",
                in = SecuritySchemeIn.HEADER
        )
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Social Media API"));
    }
}

