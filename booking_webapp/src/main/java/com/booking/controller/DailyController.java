package com.booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.booking.entity.Puesto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONArray;

@Tag(name = "Daily")
@RestController
public class DailyController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DailyController.class);
	
	@Operation(summary="Devuelve las reservas de un día")
	@GetMapping("/daily/{fecha}")
	public ResponseEntity<ArrayList<Puesto>> getDaily(@PathVariable("fecha") String fecha, Model model) {

		
		File file = new File("./src/main/resources/DailySaves/" + fecha + ".json");
		
		try(FileInputStream fis = new FileInputStream(file)){
			
			byte[] data = new byte[(int) file.length()];
			fis.read(data);

			String str = new String(data, StandardCharsets.UTF_8);
			
			JSONArray json = new JSONArray(str);
			
			ArrayList<Puesto> puestos = new ArrayList<Puesto>();

			
			for(int j=0; j<json.length();j++) {
				
				int id= json.getJSONObject(j).getInt("id");
				String tipo= json.getJSONObject(j).getString("tipo");
				boolean estado=json.getJSONObject(j).getBoolean("estado");
				String reservas=json.getJSONObject(j).getString("reservas");
						
						
						
				puestos.add(new Puesto(id,estado,tipo,reservas));
					
			}
			
			
			return new ResponseEntity<>(puestos,HttpStatus.OK);

		} catch (FileNotFoundException e) {
			log.info("Se ha intentado obtener una daily no existente: " + fecha);
			System.out.println("Se ha intentado obtener una daily no existente: " + fecha);
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (IOException e) {
			log.error("Error leyendo daily: " + fecha);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
}
