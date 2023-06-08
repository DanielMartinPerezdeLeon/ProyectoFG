package com.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.entity.Usuario;
import com.booking.entity.DatosJSON.DatosCambiarRol;
import com.booking.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UsuarioController.class);

	private static final String URLBASE = "http://localhost:8080";

	
	@Operation(summary="Devuelve datos de todos los usuarios")
	@GetMapping(value = "/todos")
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> usuarios = repository.findAll();

		log.info("Alguien ha accedido a la información de todos los usuarios");

		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	
	@Operation(summary="Cambia el rol de un usuario")
	@CrossOrigin(origins = URLBASE) // CROSS origin para hacerlo seguro
	@PostMapping(value = "/cambiar_rol")
	public ResponseEntity<HttpStatus> reservar(@RequestBody DatosCambiarRol datos) {
		Usuario usuario = repository.getUsuarioByIdentificacion(datos.getUsuario());

		if (usuario == null) {
			log.info("Alguien ha intentado cambiar el rol de un usuario no existente" + datos.getUsuario());

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {

			int nuevo_rol = datos.getRol();

			repository.delete(usuario);

			usuario.setRol(nuevo_rol);

			repository.save(usuario);

			System.out.println("Se ha aceptado un usuario nuevo: " + datos.getUsuario());
			log.info("Se ha aceptado un usuario nuevo: " + datos.getUsuario());

			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	

	// Borrar Usuario
	@Operation(summary="Borra un  usuario")
	@CrossOrigin(origins = URLBASE) // CROSS origin para hacerlo seguro
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> remove(@PathVariable("id") String id) {
		Usuario usuario = repository.getUsuarioByIdentificacion(id);
		if (usuario == null) {
			log.info("Se ha intentado eliminar un usuario no existente" + id);

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			repository.delete(usuario);

			log.info("Se ha borrado un usuario: " + usuario.getIdentificacion());
			System.out.println(" Se ha borrado un usuario: " + usuario.getIdentificacion());

			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

}
