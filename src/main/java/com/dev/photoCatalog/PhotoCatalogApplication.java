package com.dev.photoCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PhotoCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoCatalogApplication.class, args);
	}

}
