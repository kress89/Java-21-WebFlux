package com.pinehood.articlebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArticleBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleBotApplication.class, args);
	}

}
