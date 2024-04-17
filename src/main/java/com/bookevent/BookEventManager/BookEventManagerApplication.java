package com.bookevent.BookEventManager;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookEventManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookEventManagerApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
