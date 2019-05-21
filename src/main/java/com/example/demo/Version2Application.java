package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.demo.api.controller" })
public class Version2Application {

	public static void main(String[] args) {
		SpringApplication.run(Version2Application.class, args);
	}

}
