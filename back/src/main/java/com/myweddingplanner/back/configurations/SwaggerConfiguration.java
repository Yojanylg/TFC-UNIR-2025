package com.myweddingplanner.back.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "My Wedding Planner API",
                description = "pendiente"
        ),
        servers = @Server(url = "httpd://localhost:8080")
)
public class SwaggerConfiguration {
}
