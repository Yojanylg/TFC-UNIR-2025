package com.myweddingplanner.back.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Wedding Planner API",
                description = "API para dar servicio a la app Android de gestión de bodas"
        ),
        servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfiguration {
}
