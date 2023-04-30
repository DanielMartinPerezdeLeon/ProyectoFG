package controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repository.UsuarioRepository;
import entity.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioRepository repository;

	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
	}

	@PostMapping(value = "/todos")
	public List<Usuario> getAll() {
		return repository.findAll();
	}

	@PostMapping(value = "/{identificador}")
	public Usuario getByIdentificador(@PathVariable String identificador) {
		try {
			System.out.println("usuario devuelto");
			return repository.getUsuarioByIdentificador(identificador);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@DeleteMapping(value = "/{identificador}")
	public boolean deleteUsuario(@PathVariable String identificador) {
		try {
			Usuario usuario = repository.getUsuarioByIdentificador(identificador);
			repository.delete(usuario);
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
