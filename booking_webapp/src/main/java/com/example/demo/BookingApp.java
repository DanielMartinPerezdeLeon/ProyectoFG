package com.example.demo;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.reactive.result.view.RedirectView;

@SpringBootApplication
@Controller
public class BookingApp {

	public static void main(String[] args) {
		SpringApplication.run(BookingApp.class, args);
	}

	@GetMapping("/")
	public String mover() {
		return "redirect:/inicio";
	}
	
	@RequestMapping("/inicio")
	public String paginaInicio() {
		return "index";
	}

}
