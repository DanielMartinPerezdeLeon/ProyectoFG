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

			Usuario encontrado = (Usuario) repository.getUsuarioByIdentificacion(usuario.getIdentificacion());
			
			//Si existe un usuario con ese identificador
			if (encontrado != null || encontrado.getIdentificacion().equalsIgnoreCase(usuario.getIdentificacion())) {
				System.out.println("Se ha intentado registrar un usuario ya creado: " + usuario.getIdentificacion());
				return "index"; // TODO devolver al usuario que ese identificador ya existe
				
			//sino
			} else {
				//REGISTRA USUARIO
				usuario.setRol(0);
				usuario.setContrasena(Base64.getEncoder().encode(usuario.getContrasena())); // CODIFICA LA CONTRASEÑA
				repository.save(usuario);
				System.out.println("Se ha registrado un nuevo usuario: " + usuario.getIdentificacion());
				return "usuario_registrado"; // TODO página indicando que se ha registrado correctamente y espere a que
												// el administrador le acepte
			}
		
	    //EXCEPCION
		} catch (Exception e) {
			System.err.println(e.getMessage());
			model.addAttribute("error", new String("Error, grave, póngase en contacto con su administrador"));
			return ("/error");
		}

	}
}
