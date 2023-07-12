package com.example.segundoteste;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SegundotesteApplication {
	public static void main(String[] args) {
		SpringApplication.run(SegundotesteApplication.class, args);
	}
}