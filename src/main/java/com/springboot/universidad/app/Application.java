package com.springboot.universidad.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "37093729";
		
		String bcryptPassword = passwordEncoder.encode(password);
		System.out.println(bcryptPassword);
		
		password = "29790127";
		bcryptPassword = passwordEncoder.encode(password);
		System.out.println(bcryptPassword);
	}

}