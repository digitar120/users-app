package com.digitar120.usersapp.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // Bean fundamental
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()

                // Escanear elementos y clases anotadas con @RestController
                .apis(RequestHandlerSelectors.basePackage("com.digitar120.usersapp.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Users API",
                "API REST para interactuar con una base de datos de usuarios",
                "v1",
                "Terms of Service URL",
                "DIGITAR",
                "License name",
                "API License URL");
    }

}
