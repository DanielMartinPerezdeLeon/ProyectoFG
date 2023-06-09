package com.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.entity.Usuario;
import com.booking.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	private final UsuarioRepository repository;
	
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository=repository;
	}
	
	
	//CRUD
	
	public Usuario create(Usuario usuario) {	//C
		System.out.println("Usuario creado: "+usuario.getIdentificacion());
		return repository.save(usuario);
	}	
	
	
	public List<Usuario> getAll() {
		return repository.findAll();
	}
	
	public Usuario getByIdentificacion(String identificacion) {	//Coge por identificación
		List<Usuario> usuarios = getAll();
		for (Usuario usuario : usuarios) {
			if(usuario.getIdentificacion().equalsIgnoreCase(identificacion)) {
				return usuario;
			}
		}
		return null;
	}
	
	
	public boolean remove(Usuario usuario) {	//D
		try {
			repository.delete(usuario);
			System.out.println("usuario eliminado: "+usuario.getIdentificacion());
			return true;
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	
}
