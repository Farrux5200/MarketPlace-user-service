package com.company.userservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
				info = @Info(
						title ="Magazine Market Place",
						description = "Bu project Magazine Market Place uchun",
						version = "20.02.02",
						contact = @Contact(
								name = "Farruh Izzatullayev",
								url = "http://localhost:8080/swagger-ui/index.html#/product-controller",
								email = "farruxizzatullayev5200@gmail.com"
						),
						license = @License(
								name = "License name",
								url = "http://localhost:8080/swagger-ui/index.html#/product-controller"
						)
				),
				tags ={ @Tag(
						name = "Magazine Market Place Create ",
						description = "Tag description, create"
				),
						@Tag(
								name = "Magazine Market Place Get ",
								description = "Tag description, get"
						),
						@Tag(
								name = "Magazine Market Place Update ",
								description = "Tag description, update"
						),
						@Tag(
								name = "Magazine Market Place Delete ",
								description = "Tag description, delete"
						),
						@Tag(
								name = "Magazine Market Place GetAll ",
								description = "Tag description, delete"
						),
				},
				servers = @Server(
						url = "http://localhost:8080/swagger-ui/index.html#/product-controller",
						description = "Magazine Market Place project"
				)
		)

public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
