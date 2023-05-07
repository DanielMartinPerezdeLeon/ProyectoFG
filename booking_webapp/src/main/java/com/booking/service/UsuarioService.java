package com.booking.service;

import java.util.List;

import com.booking.entity.Usuario;

public interface UsuarioService {
	public Usuario create(Usuario usuario);

	public List<Usuario> getAll();

	public Usuario getByIdentificacion(String identificacion);

	
	public boolean remove(Usuario usuario);
}
