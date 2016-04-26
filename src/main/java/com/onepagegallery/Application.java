package com.onepagegallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		
	    registry.addResourceHandler("/uploads/**")
	            .addResourceLocations("/uploads/", "file:uploads/");
	}
}
