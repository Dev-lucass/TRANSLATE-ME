package com.example.TranslateMe.API;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TranslateMeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslateMeApiApplication.class, args);
        log.info("ACESSE -> http://localhost:8080");
	}

}
