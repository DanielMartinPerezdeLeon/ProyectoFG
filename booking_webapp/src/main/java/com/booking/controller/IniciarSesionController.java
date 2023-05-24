package com.booking.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booking.entity.Usuario;
import com.booking.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class IniciarSesionController {

	
	@Autowired
	private UsuarioRepository repository;

	
	// INICIAR SAESION
	@PostMapping(value = "/iniciar_sesion")
	public String comprobar(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session) {
		try {

			Usuario encontrado = repository.getUsuarioByIdentificacion(usuario.getIdentificacion());
			
			//TODO todos los loggers
			
			//siel usuario no existe en DB
			if (encontrado == null || usuario == null) {
				System.out.println("Se ha intentado sacar un usuario no existente " + (usuario.getIdentificacion()));
				model.addAttribute("error",new String("Esa identificación no existe, por favor, revise los datos o contacte un administrador"));
				return "index"; // TODO 
				
			//si existe
			} else {
				
				//Si contraseña incorrecta
				if(encontrado.ContrasenaDescodificada().equalsIgnoreCase(usuario.getContrasena())==false) {
					System.out.println("Se ha intentado logear en un usuario con una contraseña incorrecta: " + (usuario.getContrasena())+" "+encontrado.ContrasenaDescodificada());
					model.addAttribute("error",new String("Contraseña incorrecta"));
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
			// TODO logger
			model.addAttribute("error", new String("error grave, pongase en contacto con su administrador"));
			return "error"; // TODO página /error
		}
	}


	//DESCONECTARSE
	@GetMapping(value = "/desconectarse")
	public String desconectarse(Model model, HttpSession session) {
		try {
			Usuario usu = (Usuario) session.getAttribute("usuario");
			//Si se va a salir de usuario_registrado
			if (usu == null || usu.getIdentificacion().isEmpty()) {
				System.out.println("Alguien no logeado va a salir (volver a inicio?)");
			//Si el usuario estaba logeado
			} else {
				System.out.println("Un usuario se va ha desconectar: " + usu.getIdentificacion());
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
			model.addAttribute("error", new String("Error, grave, póngase en contacto con su administrador"));
			return ("error");
		}
	}

}
