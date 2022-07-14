package com.example.proyectoIntegrador;

import com.example.proyectoIntegrador.auth.model.ERole;
import com.example.proyectoIntegrador.auth.model.Role;
import com.example.proyectoIntegrador.auth.repository.RepositoryRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@ImportAutoConfiguration
@RestController
public class ProyectoIntegradorApplication {

	@Autowired
	RepositoryRole repository;


	public static void main(String[] args) {
		SpringApplication.run(ProyectoIntegradorApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");//.allowedOrigins("https://bookingdh.xyz").allowedMethods("*").allowedHeaders("*");
			}
		};
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods("*").allowedHeaders("*");
//			}
//		};
//	}

}
