package com.example.Ecommerce;

import com.example.Ecommerce.service.FilesStorageService;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;

@SpringBootApplication
public class ECommerceApplication  {





	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);
		System.out.println("Hello");


	}



}
