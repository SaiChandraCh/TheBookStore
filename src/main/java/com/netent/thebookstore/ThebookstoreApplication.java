package com.netent.thebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.netent.thebookstore"})
public class ThebookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThebookstoreApplication.class, args);
	}
}
