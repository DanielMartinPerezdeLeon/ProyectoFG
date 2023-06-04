package com.booking;


import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;



@SpringBootApplication
@EnableScheduling
@Slf4j 
public class BookingApp {

	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookingApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookingApp.class, args);
		LocalTime localTime = LocalTime.now();
		System.out.println("Aplicacion iniciada: "+localTime);
		log.info("Aplicacion iniciada: "+localTime);
	}
	
	


}
