package com.example.miniProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.miniProject.model") // Đặt tên package cho quét các entity
@EnableJpaRepositories(basePackages ="com.example.miniProject.repository") // Đặt tên package cho quét các repository
@ComponentScan("com.example.miniProject")
public class MiniProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniProjectApplication.class, args);
	}

}
