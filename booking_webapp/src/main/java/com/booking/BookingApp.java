package com.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booking.entity.Usuario;

import jakarta.servlet.http.HttpSession;

@SpringBootApplication
@Controller
public class BookingApp {

	public static void main(String[] args) {
		SpringApplication.run(BookingApp.class, args);
	}
	
	
	@Autowired
    private HttpSession httpSession;

	@RequestMapping("/inicio")
	@GetMapping("/inicio") // Si no añadimos nada a la URI nos redirecciona a /index
	public String moverALogin(Model model, HttpSession session) {
		String error = "";
		model.addAttribute("error", error);
		
		Usuario usuario= (Usuario) session.getAttribute("usuario");


		// Sesion no guardada (TODO sesion???)
		if (usuario==null) {
			usuario = new Usuario();
			model.addAttribute("usuario", usuario);
			return "index";
		} else {
			
			if (usuario.getIdentificacion().isEmpty()) {
				usuario = new Usuario();
				model.addAttribute("usuario", usuario);
				return "index";
			} else {
				
				return "redirect:/home";
			}

		}

	}

	@GetMapping("/") // Si no añadimos nada a la URI nos redirecciona a /index
	public String moverInicioSesion(Model model) {

		return "redirect:/inicio";

	}

	@GetMapping("/home") // Si no añadimos nada a la URI nos redirecciona a /index
	public String moverHome(Model model, HttpSession session) {
		
		Usuario usuario= (Usuario) session.getAttribute("usuario");
		if(usuario!=null && !usuario.getIdentificacion().isBlank()) {
			model.addAttribute("usuario",usuario);
			System.out.println(usuario.getIdentificacion()+session.getCreationTime());
			return "home";
		}else {
			return"index";
		}
		

	}


}
