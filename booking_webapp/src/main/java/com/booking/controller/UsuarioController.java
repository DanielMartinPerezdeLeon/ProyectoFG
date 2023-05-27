package com.booking.controller;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.entity.Usuario;
import com.booking.entity.DatosJSON.DatosCambiarRol;
import com.booking.entity.DatosJSON.DatosReserva;
import com.booking.repository.UsuarioRepository;




@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
    private UsuarioRepository repository;

    @GetMapping(value="crear")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario usuariocreado = repository.save(usuario);
        return new ResponseEntity<>(usuariocreado, HttpStatus.CREATED);
    }

    @GetMapping(value="/todos")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = repository.findAll();
        
        
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/cambiar_rol")
	public void reservar(@RequestBody DatosCambiarRol datos) {
		Usuario usuario = repository.getUsuarioByIdentificacion(datos.getUsuario());

		int nuevo_rol=datos.getRol();
		

		repository.delete(usuario);
		
		usuario.setRol(nuevo_rol);
		
		repository.save(usuario);
		
		
	
		System.out.println(" Se ha aceptado un usuario nuevo: "+datos.getUsuario());
	}

    /*
    @PostMapping("/{identificacion}")
    public ResponseEntity<Usuario> getByIdentificacion(@PathVariable("identificacion") String identificacion) {
        Usuario usuario = usuarioService.getByIdentificacion(identificacion);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{identificacion}")
    public ResponseEntity<HttpStatus> remove(@PathVariable("id") String identificacion) {
        Usuario usuario = usuarioService.getByIdentificacion(identificacion);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean success = usuarioService.remove(usuario);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
 */
}
