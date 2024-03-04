package com.banquito.core.banking.creditos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.banquito.core.banking.creditos")
public class CreditosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditosApplication.class, args);
	}

}
