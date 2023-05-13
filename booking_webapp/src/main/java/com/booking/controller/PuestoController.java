package com.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.entity.Puesto;
import com.booking.repository.PuestoRepository;

@RestController
@RequestMapping("/puestos")
public class PuestoController {
	
	@Autowired
	private PuestoRepository repository;

	@GetMapping(value="/todos")
    public ResponseEntity<List<Puesto>> getAll() {
        List<Puesto> usuarios = repository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
