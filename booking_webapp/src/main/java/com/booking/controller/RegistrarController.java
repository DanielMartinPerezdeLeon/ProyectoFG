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
public class RegistrarController {
	
	
	@Autowired
	private UsuarioRepository repository;
	
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrarController.class);
	
	
	
	//Ir a registrar
	@GetMapping(value = "/registrarse")
	public String moverseRegistrarse(Model model) {
		model.addAttribute("nuevo_usuario", new Usuario());
		return "registrarse";
	}
	

	// REGISTRAR
	@PostMapping(value = "/registrar_usuario")
	public String registrarUsuario(@ModelAttribute("nuevo_usuario") Usuario usuario, Model model) {
		try {

			Usuario encontrado = repository.getUsuarioByIdentificacion(usuario.getIdentificacion());
			
			//Si existe un usuario con ese identificador
			if (encontrado != null) {
				System.out.println("Se ha intentado registrar un usuario ya creado: " + usuario.getIdentificacion());
				model.addAttribute("error","Esa identificación ya existe");
				log.info("Se ha intentado registrar un usuario con una identificación ya existente: "+usuario.getIdentificacion());
				
				return "registrarse";
				
			//sino
			} else {
				//REGISTRA USUARIO
				usuario.setRol(0);
				usuario.setContrasena(new String(Base64.getEncoder().encode(usuario.getContrasena().getBytes()))); // CODIFICA LA CONTRASEÑA
				repository.save(usuario);
				
				System.out.println("Se ha registrado un nuevo usuario: " + usuario.getIdentificacion());
				log.info("Se ha registrado un nuevo usuario: " + usuario.getIdentificacion());
				
				return "usuario_registrado"; 
			}
		
	    //EXCEPCION
		} catch (Exception e) {
			System.err.println(e.getMessage());
			model.addAttribute("error", "Error, grave, póngase en contacto con su administrador");
			log.error("Error grave: "+e.getMessage());
			
			return ("/error");
		}

	}
}
