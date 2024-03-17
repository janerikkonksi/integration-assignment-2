package com.esi.orderservice;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class OrderServiceApplication {

// A Bean is an object that we can inject in any class and  stored in an IoC (Inversion of Control) container which is often referred as Application Context.
// Using the IoC principle, Spring collects bean instances from our application and uses them at the appropriate time.
@Bean
    // WebClient.Builder is mutable builder for creating a WebClient.
    // WebClient is an interface representing the main entry point for performing web requests. 
    // It is a part of Spring Web Reactive module and will be replacing the classic RestTemplate. 
    // Unlike RestTemplate (synchronous), WebClient  is a reactive (asynchronous), non-blocking solution that works over the HTTP/1.1 protocol.
	public WebClient.Builder getWebClientBuilder() {
		return  WebClient.builder();
	}

	public static void main(String[] args) {
		DatabaseInitializer.initialize("orderservice_db");
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
			.info(new Info().title("Customers - Warehouse API")
				.description("Customers - Warehouse API")
				.version("v0.0.1")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")))
			.externalDocs(new ExternalDocumentation()
				.description("Warehouse API - Customers Documentation")
				.url("http://localhost:8083/swagger-ui/index.html"));
	}
}
