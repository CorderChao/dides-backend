package com.college.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI collegeManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("College Management System API")
                        .description("API for managing college applications, students, and courses for DIDES")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("DDES Support")
                                .email("dides@example.com")
                                .url("https://dides.ac.tz"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}