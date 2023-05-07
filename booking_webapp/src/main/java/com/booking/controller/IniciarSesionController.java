package com.booking.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.booking.entity.Usuario;
import com.booking.repository.UsuarioRepository;

@Controller
public class IniciarSesionController {

	
	@Autowired
	private UsuarioRepository repository;

	
	// INICIAR SAESION
	@PostMapping(value = "/iniciar_sesion")
	public String comprobar(@ModelAttribute("usuario") Usuario usuario, Model model) {
		try {

			Usuario encontrado = repository.getUsuarioByIdentificacion(usuario.getIdentificacion());
			
			//siel usuario no existe en DB
			if (encontrado == null || usuario == null) {
				System.out.println("Se ha intentado sacar un usuario no existente " + (usuario.getIdentificacion()));
				return "index"; // TODO volver a inicio_sesion sacando error
				
			//si existe
			} else {
				model.addAttribute("usuario",encontrado); 	//Pone al encontrado como usuario en el modelo
				System.out.println(encontrado.getNombre());
				return "home"; // TODO acceder a la página
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
	@PostMapping(value = "/desconectarse")
	public String desconectarse(Model model) {
		try {
			Usuario usu = (Usuario) model.getAttribute("usuario");
			//Si se va a salir de usuario_registrado
			if (usu == null || usu.getIdentificacion().isEmpty()) {
				System.out.println("Alguien no logeado va a salir (volver a inicio?)");
			//Si el usuario estaba logeado
			} else {
				System.out.println("Un usuario se va ha desconectar: " + usu.getIdentificacion());
			}
			model.addAttribute("usuario", null);
			model.addAttribute("error", null);
			model.addAttribute("nuevo_usuario", null);
			return("redirect:/");
		
		//excepcion
		} catch (Exception e) {
			System.err.println(e.getMessage());
			model.addAttribute("error", new String("Error, grave, póngase en contacto con su administrador"));
			return ("error");
		}
	}

}
