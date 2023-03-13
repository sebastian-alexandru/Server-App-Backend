package com.example.nginx;

import com.example.nginx.model.Server;
import com.example.nginx.repo.ServerRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static com.example.nginx.enumeration.Status.SERVER_DOWN;
import static com.example.nginx.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class NginxApplication {

	public static void main(String[] args) {
		SpringApplication.run(NginxApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Apache", "16 GB", "Personal PC", "http://localhost:8080/server/image/apache.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.161", "Docker", "32 GB", "Personal PC 2", "http://localhost:8080/server/image/docker.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.162", "Localhost", "8 GB", "Personal PC 3", "http://localhost:8080/server/image/localhost.png", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.163", "Nginx", "16 GB", "Personal PC 4", "http://localhost:8080/server/image/nginx.png", SERVER_DOWN));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
