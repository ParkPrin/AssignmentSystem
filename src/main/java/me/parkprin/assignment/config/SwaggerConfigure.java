package me.parkprin.assignment.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.*;

@EnableOpenApi
@Configuration
public class SwaggerConfigure {

    @Bean
    public Docket multipartApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("all-api")
                .apiInfo(apiInfo())
                .select()
                .paths(allPaths())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Parkprin API")
                .description("주석")
                .termsOfServiceUrl("http://springfox.io")
                .contact(new Contact("springfox", "", ""))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("3.0")
                .build();
    }

    private Predicate<String> allPaths() {
        return regex(".*/api/users.*")
                .or(regex(".*/api/products.*")
                        .or(regex(".*/api/reviews.*")
                            .or(regex(".*/api/orders.*"))));

    }
}
