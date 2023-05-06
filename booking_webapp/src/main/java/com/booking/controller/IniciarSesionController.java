package com.booking.controller;

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

	@PostMapping(value = "/iniciar_sesion")
	public String comprobar(@ModelAttribute("usuario") Usuario usuario,Model model) {
		try {

			Usuario encontrado = repository.getUsuarioByIdentificacion(usuario.getIdentificacion());
			if (encontrado == null || usuario == null) {
				System.out.println("Se ha intentado sacar un usuario no existente " + (usuario.getIdentificacion()));
				return "index"; // TODO volver a inicio_sesion sacando error
			} else {
				System.out.println(encontrado.getNombre());
				return "resultado"; // TODO acceder a la página
			}

		} catch (Exception t) {
			System.out.println(t.getMessage());
			//TODO logger
			model.addAttribute("error",new String("error grave, pongase en contacto con su administrador"));
			return "error"; // TODO página /error
		}
	}

	@GetMapping(value = "/registrarse")
	public String moverseRegistrarse(Model model) {
		model.addAttribute("nuevo_usuario", new Usuario());
		return "registrarse";
	}

	@PostMapping(value = "/registrar_usuario")
	public String registrarUsuario(@ModelAttribute("nuevo_usuario") Usuario usuario, Model model) {
		try {

			Usuario encontrado = (Usuario) repository.getUsuarioByIdentificacion(usuario.getIdentificacion());

			if (encontrado != null) {
				System.out.println("Se ha intentado registrar un usuario ya creado: "+usuario.getIdentificacion());
				return "index"; //TODO devolver al usuario que ese identificador ya existe
			}else {
				usuario.setRol(0);
				repository.save(usuario);
				System.out.println("Se ha registrado un nuevo usuario: "+usuario.getIdentificacion());
				return "usuario_registrado";	//TODO página indicando que se ha registrado correctamente y espere a que el administrador le acepte
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			model.addAttribute("error",new String ("Error, grave, póngase en contacto con su administrador"));
			return ("/error");
		}

	}

}
