package com.tinotenda.usertask.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Value("${swagger.api.info.contact.email}")
    private String contactEmail;

    @Value("${swagger.api.info.contact.name}")
    private String contactPerson;

    @Value("${swagger.api.info.title}")
    private String applicationTitle;

    @Value("${swagger.api.info.description}")
    private String applicationDescription;

    @Value("${swagger.api.info.version}")
    private String applicationVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationTitle)
                        .version(applicationVersion)
                        .contact(new Contact()
                                .name(contactPerson)
                                .email(contactEmail))
                        .description(applicationDescription)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
