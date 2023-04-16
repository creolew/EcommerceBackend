package com.example.Ecommerce;

import com.example.Ecommerce.service.FilesStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {


	@Resource
	FilesStorageService storageService;
	public static void main(String[] args) {

		SpringApplication.run(ECommerceApplication.class, args);
		System.out.println("Hello");

	}

	@Override
	public void run(String... arg) throws Exception {
//    	storageService.deleteAll();
		storageService.init();
	}

}
