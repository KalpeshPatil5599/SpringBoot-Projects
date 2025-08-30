package com.moviemanagementapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Movie Management System API",
				version = "1.0",
				description = "API documentation for managing movies, including CRUD operations and pagination.",
				contact = @Contact(
						name = "Kalpesh Patil",
						email = "kalpeshpatil5599@gmail.com",
						url = "https://github.com/kalpeshpatil5599"
				)

)
)
public class MoviemanagementapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviemanagementapiApplication.class, args);
	}



	public OpenAPI getOpenApi(){
		return new OpenAPI();
	}

}
