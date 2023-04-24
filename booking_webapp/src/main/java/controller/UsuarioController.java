package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import repository.UsuarioRepository;
import entity.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioRepository repository;
	
	public UsuarioController(UsuarioRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping
	public List<Usuario> getAll(Model model) {
		model.addAttribute("usuarios", repository.findAll());
		return repository.findAll();
	}
	
	@

}
