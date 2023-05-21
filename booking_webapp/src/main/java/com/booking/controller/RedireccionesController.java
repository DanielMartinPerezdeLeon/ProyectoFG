package com.booking.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.hibernate.internal.build.AllowSysOut;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booking.entity.Puesto;
import com.booking.entity.Usuario;
import com.booking.repository.PuestoRepository;
import com.booking.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class RedireccionesController {
	
	
	@Autowired
    private HttpSession httpSession;
	
	@Autowired
	private UsuarioRepository usuario_repository;
	
	@Autowired
	private PuestoRepository puesto_repository;
	

	@RequestMapping("/inicio")
	@GetMapping("/inicio") 
	public String moverALogin(Model model, HttpSession session) {
		String error = "";
		model.addAttribute("error", error);
		
		Usuario usuario= (Usuario) session.getAttribute("usuario");


		// Sesion no guardada (TODO sesion???)
		if (usuario==null) {
			usuario = new Usuario();
			model.addAttribute("usuario", usuario);
			return "index";
		} else {
			
			if (usuario.getIdentificacion().isEmpty()) {
				usuario = new Usuario();
				model.addAttribute("usuario", usuario);
				return "index";
			} else {
				
				return "redirect:/home";
			}

		}

	}
	
	

	@GetMapping("/") // Si no añadimos nada a la URI nos redirecciona a /index
	public String moverInicioSesion(Model model) {

		return "redirect:/inicio";

	}
	

	//HOME
	@GetMapping("/home") 
	public String moverHome(Model model, HttpSession session) {
		
		Usuario usuario= (Usuario) session.getAttribute("usuario");
		if(usuario!=null && !usuario.getIdentificacion().isBlank()) {
			model.addAttribute("usuario",usuario);
			System.out.println(usuario.getIdentificacion()+session.getCreationTime());
			
			URL urljson;
			try {
				urljson = new URL("http://localhost:8080/puestos/todos");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				String error = "Error obteniendo url JSON";
				model.addAttribute("error", error);
				e.printStackTrace();
				return "error";
			}
			
			JSONArray json=getJson(urljson);

			
			int[] horas = new int[json.length()];
			
			for(int i=0; i<json.length(); i++) {
				
				JSONArray a = new JSONArray(json.getJSONObject(i).getString("reservas"));
				int horas_libres=0;
				
				for(int j=0; j<a.length();j++) {
					if((a.getJSONObject(j).getString("detalle").isBlank())) {
						horas_libres++;
						
					}
				}
				
				horas[i]=horas_libres;
			}
			
			System.out.println("usuario entra: "+usuario.getIdentificacion());
			model.addAttribute("horas",horas);
			model.addAttribute("puestos",json);
			

			
			
		
			return "home";
		}else {
			model.addAttribute("usuario",null);
			return"redirect:/";
		}
		

	}
	
		
	//Lee JSON de una url
	public static JSONArray getJson(URL url) {
	    String json;
		try {
			json = IOUtils.toString(url, Charset.forName("UTF-8"));
			return new JSONArray(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	    
	}
	
	
	//USUARIOS
		@GetMapping("/ver_usuarios") 
		public String moverUsuarios(Model model, HttpSession session) {
			
			Usuario usuario= (Usuario)session.getAttribute("usuario");
			
			if(usuario != null && !usuario.getIdentificacion().isEmpty() && usuario.getRol()>1) {
				URL urljson;
				try {
					urljson = new URL("http://localhost:8080/usuarios/todos");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					String error = "Error obteniendo url JSON";
					model.addAttribute("error", error);
					e.printStackTrace();
					return "error";
				}
				
				JSONArray json=getJson(urljson);
				
				System.out.println(usuario.getIdentificacion()+" accediendo a la información de todos los usuarios");
				
				model.addAttribute("lista_usuarios",json);
				
				return("usuarios");
			}
			
			return("redirect:/");
		}
}
