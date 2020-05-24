package com.netent.thebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
	author: Sai Chandra Chilupui
	last updated date: Sun, 24 May 2020
*/
@SpringBootApplication
@ComponentScan({"com.netent.thebookstore"})
public class ThebookstoreApplication {
/* This is main method of THE BOOK STORE app*/
	public static void main(String[] args) {
		SpringApplication.run(ThebookstoreApplication.class, args);
	}
}
