package com.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.booking.entity.Usuario;



@SpringBootApplication
@Controller
public class BookingApp {

	public static void main(String[] args) {
		SpringApplication.run(BookingApp.class, args);
	}

	
	
	@GetMapping("/")	//Si no añadimo nada a la URI nos redirecciona a /inicio
	public String mover() {
		return "redirect:/inicio";
	}
	
	@GetMapping("/inicio")	//Abre index.html (inicio de sesion)
	public String paginaInicio(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario",usuario);
		return "index";
	}
	
	


}
