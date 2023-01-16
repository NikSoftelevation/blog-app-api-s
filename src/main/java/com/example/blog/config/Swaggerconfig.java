package com.example.blog.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swaggerconfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public ApiKey apiKeys() {

		return new ApiKey("JWT", AUTHORIZATION_HEADER, "Header");

	}

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();

	}

	private ApiInfo getInfo() {

		return new ApiInfo(" Bloging Application : Backend Course", "This project is developed by Nikhil Sharma", "1.0",
				"terms of service", new Contact("Nikhil", "www.softelevation.com/", "nikhil.softelevation@gmail.com"),
				"License of APIS", "API license URL", java.util.Collections.emptyList());
	}
}