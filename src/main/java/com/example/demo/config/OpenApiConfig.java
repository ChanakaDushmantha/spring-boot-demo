package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */
@OpenAPIDefinition(
        info = @Info(
                description = "Api documentation for Demo Management",
                title = "Demo Management Service",
                version = "1.0"
        ),
//        security = {
//                @SecurityRequirement(
//                        name = "Bearer Authentication"
//                )
//        },
        servers = {
                @Server(url = "${server.url}", description = "Server"),
        })
//@SecurityScheme(
//        name = "Bearer Authentication",
//        description = "JWT authentication for local users",
//        scheme = "bearer",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        in = SecuritySchemeIn.HEADER
//)
public class OpenApiConfig {
}
