package com.booking.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.Path;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {
	
	ArrayList<Tag> tags= new ArrayList();

	 @Bean
	  public OpenAPI customOpenAPI() {
		 
		 tags.add(new Tag().name("Usuarios").description("Operaciones relacionadas con los usuarios"));
		 tags.add(new Tag().name("Puestos").description("Operaciones relacionadas con los usuarios"));
		 
		 
	    return new OpenAPI()
	    	.tags(tags)
	        .components(new Components())
	        .info(new Info()
	            .title("Booking Application API")
	            .description("Esta es la documentación de la API usada por Booking Application.")
	            .termsOfService("terms")
	            .contact(new Contact().email("damarpele@gmail.com").name("Daniel Martín Pérez de León"))
	            .license(new License().name("GNU"))
	            .version("1.0")
	        );
	  }
	 
	 
	 
}
