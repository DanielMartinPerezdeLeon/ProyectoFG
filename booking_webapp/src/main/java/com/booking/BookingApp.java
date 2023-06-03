package com.booking;


import java.time.LocalTime;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class BookingApp {


	public static void main(String[] args) {
		SpringApplication.run(BookingApp.class, args);
		LocalTime localTime = LocalTime.now();
		System.out.println("Aplicacion iniciada: "+localTime);
		logger.info("Aplicacion iniciada: "+localTime);
	}
	
	


}
