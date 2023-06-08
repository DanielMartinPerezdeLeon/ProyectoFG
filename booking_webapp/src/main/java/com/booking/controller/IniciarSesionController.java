package com.booking.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.booking.BookingApp;
import com.booking.entity.Usuario;
import com.booking.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class IniciarSesionController {

	
	@Autowired
	private UsuarioRepository repository;
	
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IniciarSesionController.class);
	
	static final String ERRORLOGEO= "Alguien ha intentado logearse incorrectamente";


	
	// INICIAR SAESION
	@PostMapping(value = "/iniciar_sesion")
	public String comprobar(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session) {
		try {

			Usuario encontrado = repository.getUsuarioByIdentificacion(usuario.getIdentificacion());
			
			
			//siel usuario no existe en DB
			if (encontrado == null) {
				System.out.println("Se ha intentado sacar un usuario no existente " + (usuario.getIdentificacion()));
				model.addAttribute("error","Esa identificación no existe, por favor, revise los datos o contacte un administrador");
				log.info("Se ha intentado sacar un usuario no existente " + (usuario.getIdentificacion()));
				
				return "index";
				
			//si existe
			} else {
				
				//Si contraseña incorrecta
				if(!encontrado.ContrasenaDescodificada().equalsIgnoreCase(usuario.getContrasena())) {
					System.out.println("Se ha intentado logear en un usuario con una contraseña incorrecta: " + (usuario.getIdentificacion()));
					model.addAttribute("error","Contraseña incorrecta");
					log.info("Se ha intentado logear en un usuario con una contraseña incorrecta: " + (usuario.getIdentificacion()));
					
					return "index";
				}
				
				
				//Si su rol es 0 (no está aceptado)
				if(encontrado.getRol()<1) {
					System.out.println("Se ha intentado logear un usuario aun no aceptado: " + (usuario.getIdentificacion()));
					model.addAttribute("error","Su cuenta todavía no ha sido aceptada, contacte con un manager o administrador");
					log.info("Se ha intentado logear un usuario aun no aceptado: " + (usuario.getIdentificacion()));
					
					return "index";
				}
				
				session.setAttribute("usuario", encontrado);
				model.addAttribute("usuario",encontrado); 	//Pone al encontrado como usuario en el modelo
				System.out.println(encontrado.getNombre());
				
				return "redirect:/home";
			}
		
		//excepcion
		} catch (Exception t) {
			System.out.println(t.getMessage());
			model.addAttribute("error", "error grave, pongase en contacto con su administrador");
			log.error("Error grave: "+usuario.getIdentificacion()+" - "+t.getMessage());
			
			return "error";
		}
	}


	//DESCONECTARSE
	@GetMapping(value = "/desconectarse")
	public String desconectarse(Model model, HttpSession session) {
		try {
			Usuario usu = (Usuario) session.getAttribute("usuario");
			//Si se va a salir de usuario_registrado
			if (usu == null || usu.getIdentificacion().isEmpty()) {
				System.out.println("Alguien no logeado va a salir (volver a inicio)");
				log.info("Alguien no logeado va a salir (volver a inicio)");
				
			//Si el usuario estaba logeado
			} else {
				System.out.println("Un usuario se va ha desconectar: " + usu.getIdentificacion());
				log.info("Un usuario se va ha desconectar: " + usu.getIdentificacion());
				
			}
			
			session.setAttribute("usuario", null);
			model.addAttribute("usuario", null);
			model.addAttribute("error", null);
			model.addAttribute("nuevo_usuario", null);
			
			session.invalidate();
			
			return("redirect:/");
		
		//excepcion
		} catch (Exception e) {
			System.err.println(e.getMessage());
			model.addAttribute("error","Error, grave, póngase en contacto con su administrador");
			log.error("Error grave:  - "+e.getMessage());
			
			return ("error");
		}
	}

}
