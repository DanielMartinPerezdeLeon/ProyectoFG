package com.booking.scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.File;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.xstream.CatchAllConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.booking.entity.Puesto;
import com.booking.repository.PuestoRepository;


@Component
public class CopiaDiariaPuestos {
	
	@Autowired
	private PuestoRepository repository;

	//Copia los puestos actuales a las Y REINICIA todos los horarios
	@Scheduled(cron = "30 59 23 * * *") //segundo:30 minuto:59 hora:23 dias:todos meses:todos
	public void copiaPuestos() {
		
		LocalDateTime localTime = LocalDateTime.now();
		
		
		String ruta ="./src/main/resources/DailySaves/";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDateTime = localTime.format(formatter);
		String nombre=formattedDateTime+".json";
		
		System.out.println("Copiando y reiniciando");
		
		
		try {
 
			File file = new File(ruta+nombre);
			
			if(file.createNewFile()) {	//Cierra solo el file, ta bien
				try(FileWriter output= new FileWriter(file)){
				
				JSONArray array= new JSONArray(repository.findAll());
				
				output.write(array.toString());
				
				}catch(IOException e) {
					System.err.println(e);
				}
			}
			
			
			
		}catch(Exception e) {
			System.err.println(e);
		}
		
		
		
		List<Puesto> puestos = repository.findAll();
		
		for(Puesto puesto: puestos) {
			repository.delete(puesto);
			puesto.setReservasDefault();
			repository.save(puesto);
		}
				
	}
	

}
