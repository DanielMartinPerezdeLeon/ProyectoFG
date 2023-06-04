package com.booking.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.booking.entity.Puesto;
import com.booking.entity.DatosJSON.DatosCambiarEstado;
import com.booking.entity.DatosJSON.DatosReserva;
import com.booking.repository.PuestoRepository;

@Controller
@RestController
@RequestMapping("/puestos")
public class PuestoController {

	@Autowired
	private PuestoRepository repository;
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PuestoController.class);
	
	private static final String URLBASE="http://localhost:8080";
	

	@GetMapping(value = "/todos")
	public ResponseEntity<List<Puesto>> getAll() {
		List<Puesto> puestos = repository.findAll();
		log.info("Se ha sacado información de todos los puestos");
		
		return new ResponseEntity<>(puestos, HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = URLBASE) //CROSS origin para hacerlo seguro
	@PostMapping(value = "/reservar")
	public ResponseEntity<HttpStatus>reservar(@RequestBody DatosReserva datos) {
		Puesto puesto = repository.getPuestoByid(datos.getPuesto());
		
		if(puesto==null) {
			log.info("Alguien ha intentado sacar información sobre puesto no existente - "+datos.getPuesto());
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {

		JSONArray jsonreservas = new JSONArray(puesto.getReservas());

		JSONObject nuevo = jsonreservas.getJSONObject(datos.getHora());
		nuevo.put("detalle", datos.getUsuario());

		jsonreservas.put(datos.getHora(), nuevo);

		Puesto puesto_nuevo = puesto;
		puesto_nuevo.setReservas(jsonreservas.toString());

		repository.delete(puesto);
		repository.save(puesto_nuevo);
	
		System.out.println(datos.getUsuario()+" ha reservado puesto "+datos.getPuesto() + " a las " +(datos.getHora()+8));
		log.info(datos.getUsuario()+" ha reservado puesto "+datos.getPuesto() + " a las " +(datos.getHora()+8));
		
		return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	
	@CrossOrigin(origins = URLBASE) //CROSS origin para hacerlo seguro
	@PostMapping(value="/cambiar_estado")
	public void cambiarEstado(@RequestBody DatosCambiarEstado datos) {
		Puesto puesto= repository.getPuestoByid(datos.getId());

		boolean nuevo_estado=datos.isEstado();
		

		repository.delete(puesto);
		
		puesto.setEstado(nuevo_estado);
		
		repository.save(puesto);
		
		
	
		System.out.println("Se ha cambiado el estado al puesto: "+datos.getId());
		log.info("Se ha cambiado el estado al puesto: "+datos.getId());
	}
	
	
	@CrossOrigin(origins = URLBASE) //CROSS origin para hacerlo seguro
	@PostMapping(value="/reiniciar")
	public ResponseEntity<HttpStatus> reiniciar(@RequestBody DatosReserva datos) {
		Puesto puesto= repository.getPuestoByid(datos.getPuesto());

		if(puesto==null) {
			log.info("Alguien ha intentado reiniciar el horario de un puesto no existente - "+datos.getPuesto());
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			repository.delete(puesto);
			puesto.setReservasDefault();
			repository.save(puesto);
		
			System.out.println("Se ha reiniciado los horario del puesto:" + datos.getPuesto());
			log.info("Se ha reiniciado los horario del puesto: " + datos.getPuesto());
			
			return new ResponseEntity<>(HttpStatus.OK);
		}

		
	}
	
	
	@CrossOrigin(origins = URLBASE) //CROSS origin para hacerlo seguro
	@PostMapping(value="/registrar")
	public RedirectView  nuevoPuesto(@ModelAttribute("nuevo_puesto") Puesto puesto, Model model) {

		List<Puesto> puestos =repository.findAll();
		
		
		int i =0;
		
		for(Puesto p: puestos) {
			if(p.getId()>i) {
				i=p.getId();
			}
		}
		
		puesto.setId(i+1);
		puesto.setReservasDefault();
		repository.save(puesto);
		
		
	
		System.out.println("Se ha creado un puesto nuevo: " + puesto.getId());
		log.info("Se ha creado un puesto nuevo: " + puesto.getId());
		
		return new RedirectView("/ver_puestos");
	}
	
	
	
	//Borrar puesto
    @CrossOrigin(origins = URLBASE) //CROSS origin para hacerlo seguro
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> remove(@PathVariable("id") int id) {
        Puesto puesto = repository.getPuestoByid(id);
        
        if (puesto == null) {
        	log.info("Alguien ha intentado eliminar un puesto no existente - "+id);
        	
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
        	
        	repository.delete(puesto);
        	
        	List<Puesto>puestos=repository.findAll();
        	
        	for(Puesto p: puestos) {
        		if(p.getId()>id) {
        			repository.delete(p);
        			p.setId(p.getId()-1);
        			repository.save(p);
        		}
        	}
        	
    		System.out.println("Se ha borrado un puesto: "+puesto.getId());
    		log.info("Se ha borrado un puesto: "+puesto.getId());
    		
        	return new ResponseEntity<>(HttpStatus.OK);
        }

    }
    
    
    @CrossOrigin(origins = URLBASE) //CROSS origin para hacerlo seguro
    @PostMapping("/reiniciar_todos")
    public ResponseEntity<HttpStatus> reiniciarTodos() {
        List<Puesto> puestos = repository.findAll();

        	for(Puesto p: puestos) {
        		repository.delete(p);
        		p.setReservasDefault();
        		repository.save(p);
        	}
        	
        	
    		System.out.println("Se han reiniciado todos los horarios");
    		log.info("Se han reiniciado todos los horarios");
    		
        	return new ResponseEntity<>(HttpStatus.OK);
    }
	

}
