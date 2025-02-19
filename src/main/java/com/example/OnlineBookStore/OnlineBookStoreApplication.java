package com.example.OnlineBookStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.OnlineBookStore.repository")
@ComponentScan("com.example.OnlineBookStore.repository")
//@EntityScan("com.example.OnlineBookStore.entity")
public class OnlineBookStoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(OnlineBookStoreApplication.class, args);
	}

}
