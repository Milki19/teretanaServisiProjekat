package com.projekat.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class Korisnicki_Servis {
	public static void main(String[] args) {
		SpringApplication.run(Korisnicki_Servis.class, args);
	}
}
