package com.gwidgets.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebsocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketServerApplication.class, args);
	}
}
