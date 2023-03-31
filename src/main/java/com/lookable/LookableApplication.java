package com.lookable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LookableApplication {

	public static void main(String[] args) {
		SpringApplication.run(LookableApplication.class, args);
	}

}
