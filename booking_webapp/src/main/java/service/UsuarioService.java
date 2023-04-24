package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import entity.Usuario;
import repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository=repository;
	}
	
	public List<Usuario> getAll(Model model) {
		model.addAttribute("usuarios", repository.findAll());
		return repository.findAll();
	}
}
