package com.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booking.entity.Usuario;





@SpringBootApplication
@Controller
public class BookingApp {

	public static void main(String[] args) {
		SpringApplication.run(BookingApp.class, args);
	}

	
	@RequestMapping("/")
	@GetMapping("/")	//Si no añadimos nada a la URI nos redirecciona a /index
	public String moverALogin(Model model) {
		String error= "";
		model.addAttribute("error",error);
		
		Usuario usuario;
		//Sesion no guardada (TODO sesion???)
		if(model.getAttribute("usuario") ==null) {
			usuario=new Usuario();
			model.addAttribute("usuario", usuario);
			return "index";
		}else { 
			usuario=(Usuario) model.getAttribute("usuario");
			if(usuario.getIdentificacion().isEmpty()) {
				usuario=new Usuario();
				model.addAttribute("usuario", usuario);
				return "index";
			}else {
				//Sesion ya iniciada (TODO Session)
				return "MenuPrincipal";
			}
			
		}
		
	}

	

	@GetMapping("/inicio")	//Si no añadimos nada a la URI nos redirecciona a /index
	public String moverALosdafgin(Model model) {

			return "redirect:/";

		}
		
	}
	
	



