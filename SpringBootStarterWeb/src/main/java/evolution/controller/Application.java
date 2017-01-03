package evolution.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import evolution.service.AnyService;

@SpringBootApplication// Don't make a mistake to write it as @SpringBootConfiguration
public class Application {
	@Bean// Define the bean right here and you can autowire it to the controller.
	public AnyService anyService() {
		return new AnyService();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);// Don't make a mistake to write it as SpringBootApplication.
	}
}
