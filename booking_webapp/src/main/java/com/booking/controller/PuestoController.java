package com.booking.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.entity.Puesto;
import com.booking.entity.DatosJSON.DatosCambiarEstado;
import com.booking.entity.DatosJSON.DatosReserva;
import com.booking.repository.PuestoRepository;

@RestController
@RequestMapping("/puestos")
public class PuestoController {

	@Autowired
	private PuestoRepository repository;
	

	@GetMapping(value = "/todos")
	public ResponseEntity<List<Puesto>> getAll() {
		List<Puesto> puestos = repository.findAll();
		return new ResponseEntity<>(puestos, HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "http://localhost:8080") //CROSS origin para hacerlo seguro
	@PostMapping(value = "/reservar")
	public void reservar(@RequestBody DatosReserva datos) {
		Puesto puesto = repository.getPuestoByid(datos.getPuesto());

		JSONArray jsonreservas = new JSONArray(puesto.getReservas());

		JSONObject nuevo = jsonreservas.getJSONObject(datos.getHora());
		nuevo.put("detalle", datos.getUsuario());

		jsonreservas.put(datos.getHora(), nuevo);

		Puesto puesto_nuevo = puesto;
		puesto_nuevo.setReservas(jsonreservas.toString());

		repository.delete(puesto);
		repository.save(puesto_nuevo);
	
		System.out.println(datos.getUsuario()+" ha reservado puesto "+datos.getPuesto() + " a las " +datos.getHora());
	}
	
	
	@CrossOrigin(origins = "http://localhost:8080") //CROSS origin para hacerlo seguro
	@PostMapping(value="/cambiar_estado")
	public void cambiarEstado(@RequestBody DatosCambiarEstado datos) {
		Puesto puesto= repository.getPuestoByid(datos.getId());

		boolean nuevo_estado=datos.isEstado();
		

		repository.delete(puesto);
		
		puesto.setEstado(nuevo_estado);
		
		repository.save(puesto);
		
		
	
		System.out.println(" Se ha cambiado el estado al puesto: "+datos.getId());
	}
	
	
	@CrossOrigin(origins = "http://localhost:8080") //CROSS origin para hacerlo seguro
	@PostMapping(value="/reiniciar")
	public void reiniciar(@RequestBody DatosReserva datos) {
		Puesto puesto= repository.getPuestoByid(datos.getPuesto());

		

		repository.delete(puesto);
		
		puesto.setReservasDefault();
		
		repository.save(puesto);
		
		
	
		System.out.println(" Se ha reiniciado los horario del puesto:" + datos.getPuesto());
	}
	

}
