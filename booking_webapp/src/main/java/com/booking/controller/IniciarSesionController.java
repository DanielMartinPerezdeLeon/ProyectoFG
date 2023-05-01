package com.booking.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.booking.entity.Usuario;
import com.booking.repository.UsuarioRepository;

import com.booking.service.UsuarioServiceImpl;


@Controller
public class IniciarSesionController{

	@Autowired
	private UsuarioRepository repository;

	@PostMapping(value = "/iniciar_sesion")
	public String comprobar(@ModelAttribute("usuario") Usuario usuario) {
		try {
			UsuarioServiceImpl servicio= new UsuarioServiceImpl(repository);
			Usuario encontrado=servicio.getByIdentificacion(usuario.getIdentificacion());
			if(encontrado==null) {
				System.out.println("Se ha intentado sacar un usuario no existente "+(usuario.getIdentificacion()));
				return "index";	//TODO pagina intermedia o js informando del error				
			}else {
				System.out.println(encontrado.getNombre());
				return "resultado";	//TODO acceder a la página
			}
			
		} catch (Exception t) {
			System.out.println("error");	
			return t.getMessage(); //TODO página /error
		}
	}
	
	@GetMapping(value = "/registrarse")
	public String moverseRegistrarse() {
		return "registrarse";
	}
	

}
