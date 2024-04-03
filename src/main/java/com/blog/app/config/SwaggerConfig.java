package com.blog.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Blog-App Api", description = "This set of API covers almost all APIs related to Blog Website", summary = "API contains summary Info", termsOfService = "Terms&ConditionsApplied...", contact = @Contact(name = "Molu Tyagi", email = "molutyagi2301@gmail.com", url = "https://www.linkedin.com/in/molu-tyagi-873a68175/"), license = @License(name = "Molu Tyagi"), version = "API/v1"), servers = {
		@Server(description = "testEnv", url = "http://localhost:8080/"),
		@Server(description = "DevEnv", url = "http://localhost:8080/") }, security = @SecurityRequirement(name = "JWT Security by Molu Tyagi"))

@SecurityScheme(name = "JWT Security by Molu Tyagi", in = SecuritySchemeIn.HEADER, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer", description = "Header Security using jwt token authentication.")

public class SwaggerConfig {

}
