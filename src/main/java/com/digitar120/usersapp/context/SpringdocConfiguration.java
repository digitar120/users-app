package com.digitar120.usersapp.context;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Springdoc configuration.
 * <p>Notes: The packages to scan are listed in the bootstrap configuration file.</p>
 * @author Gabriel Pérez (digitar120)
 */
@Configuration
public class SpringdocConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Users API")
                        .description("User's database microservice")
                        .version("v1")
                        .license(new License().name("The Game").url("https://hackertyper.com")));
    }
    }
