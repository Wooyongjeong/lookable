package com.ootd.commute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CommuteOotdApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommuteOotdApplication.class, args);
	}

}
