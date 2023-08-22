package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class S05T01N03SerranoVictorApplication {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("FlorEntityDto - Spring Boot API")
						.version("0.1")
						.description("API Spring Boot con Swagger - Manejo de información implementando las operaciones básicas")
						.termsOfService("http://swagger.io/terms")
						.license(new License().name
								("Apache Toncat 9.0.78").url("http://springdoc.org")));
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(S05T01N03SerranoVictorApplication.class, args);
	}

}
