package com.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.entity.Usuario;
import com.booking.repository.UsuarioRepository;



@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;


	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
	}

	@PostMapping(value = "/todos")
	public List<Usuario> getAll() {
		return repository.findAll();
	}


	public Usuario getByIdentificador(@PathVariable String identificador) {
		try {
			System.out.println("usuario devuelto");
			return repository.getUsuarioByIdentificacion(identificador);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	

	@DeleteMapping(value = "/{identificador}")
	public boolean deleteUsuario(@PathVariable String identificador) {
		try {
			Usuario usuario = repository.getUsuarioByIdentificacion(identificador);
			repository.delete(usuario);
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
