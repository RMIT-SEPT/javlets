package com.sept.javlets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sept.javlets.userauth.AccountController;

@SpringBootApplication
public class JavletsApplication {

	public static void main(String[] args) {		
		SpringApplication.run(JavletsApplication.class, args);
	}

}
