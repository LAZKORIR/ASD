package com.resumeai.resumescreening.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI smartResumeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Resume AI API")
                        .version("1.0.0")
                        .description("""
                                API documentation for Smart Resume AI system.<br>
                                This includes Job Management, Resume Uploading, and AI-powered Screening services.
                                """)
                        .contact(new Contact()
                                .name("Smart Resume AI Team")
                                .email("support@smartresumeai.com")
                                .url("https://smartresumeai.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server"),
                        new Server().url("https://smart-resume-ai.com").description("Production Server")
                ));
    }
}
