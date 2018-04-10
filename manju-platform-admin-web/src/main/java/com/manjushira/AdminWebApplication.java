package com.manjushira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={WebMvcAutoConfiguration.class})
public class AdminWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminWebApplication.class, args);
	}
}
