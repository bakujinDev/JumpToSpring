package com.bakujin.jump_to_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// DB 없을때
// @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class JumpToSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumpToSpringApplication.class, args);
	}
}
