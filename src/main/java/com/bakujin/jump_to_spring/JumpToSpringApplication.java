package com.bakujin.jump_to_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// @SpringBootApplication
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class JumpToSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumpToSpringApplication.class, args);
	}

}
