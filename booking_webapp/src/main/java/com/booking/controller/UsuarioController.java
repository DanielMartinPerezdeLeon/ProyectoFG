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




@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
    private UsuarioRepository repository;
	

    @GetMapping(value="/todos")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = repository.findAll();
        
        
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    
    @CrossOrigin(origins = "http://localhost:8080") //CROSS origin para hacerlo seguro
    @PostMapping(value = "/cambiar_rol")
	public ResponseEntity<HttpStatus> reservar(@RequestBody DatosCambiarRol datos) {
		Usuario usuario = repository.getUsuarioByIdentificacion(datos.getUsuario());
		
		if(usuario==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {

		int nuevo_rol=datos.getRol();
		

		repository.delete(usuario);
		
		usuario.setRol(nuevo_rol);
		
		repository.save(usuario);
	
		System.out.println(" Se ha aceptado un usuario nuevo: "+datos.getUsuario());
		return new ResponseEntity<>(HttpStatus.OK);
		}
	}

    
    //Borrar Usuario
    @CrossOrigin(origins = "http://localhost:8080") //CROSS origin para hacerlo seguro
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> remove(@PathVariable("id") String id) {
        Usuario usuario = repository.getUsuarioByIdentificacion(id);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
        	repository.delete(usuario);
        	
    		System.out.println(" Se ha borrado un usuario: "+usuario.getIdentificacion());
        	return new ResponseEntity<>(HttpStatus.OK);
        }

    }
    

}
