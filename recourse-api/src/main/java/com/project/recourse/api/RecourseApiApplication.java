package com.project.recourse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RecourseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecourseApiApplication.class, args);
	}

}
