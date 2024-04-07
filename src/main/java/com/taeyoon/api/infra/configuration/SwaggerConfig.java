package com.taeyoon.api.infra.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer")
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("andalos openapi")
                        .version("1.0")
                        .description("springdoc-openapi swagger-ui 화면")
                );
    }

    @Bean
    @Profile({"dev"})
    public GroupedOpenApi apiV1() {
        String[] paths = {"/**/v1/**"};
        String[] packagesToScan = {"com.taeyoon.api.interfaces"};
        return GroupedOpenApi.builder().group("v1")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }

//    @Bean
//    public GroupedOpenApi apiV2() {
//        String[] paths = {"/api/**/v1/**"};
//        String[] packagesToScan = {"com.taeyoon.andalos.interfaces"};
//        return GroupedOpenApi.builder().group("v2")
//                .pathsToMatch(paths)
//                .packagesToScan(packagesToScan)
//                .build();
//    }
}
