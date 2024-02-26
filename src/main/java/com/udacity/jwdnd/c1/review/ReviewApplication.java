package com.udacity.jwdnd.c1.review;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}


/*
	@Bean
	String message (){

		return "Hello, Spring!";
	}

	@Bean
	String uppercaseMessage(MessageService messageService){
		System.out.println("uppercaseMessage triggered");
		System.out.println(messageService.uppercase());

		return messageService.uppercase();

	}

	@Bean
	String lowercaseMessage(MessageService messageService) {
		System.out.println("lowercaseMessage triggered");
		System.out.println(messageService.lowercase());
		return messageService.lowercase();
	}
*/

	@PostConstruct
	void getInitMessage(){
		System.out.println("message bean created");
	}
}

