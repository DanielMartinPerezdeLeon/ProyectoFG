package com.booking.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	static final String ERRORJSON= "Error obteniendo url JSON";
	static final String BASEURL= "http://localhost:8080";
	


	@GetMapping("/inicio") 
	public String moverALogin(Model model, HttpSession session) {
		String error = "";
		model.addAttribute("error", error);
		
		Usuario usuario= (Usuario) session.getAttribute("usuario");


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

			
			URL urljson;
			try {
				urljson = new URL(BASEURL+"/puestos/todos");
			} catch (MalformedURLException e) {
				
				model.addAttribute("error", ERRORJSON);
				e.printStackTrace();
				return "error";
			}
			
			JSONArray json=getJson(urljson);
			
			if(json==null) {
				model.addAttribute("error", "El JSON es nulo");
				return "/error";
			}

			
			int[] horas = calcularHorasLibres(json);
			
			System.out.println("usuario entra: "+usuario.getIdentificacion());
			model.addAttribute("horas",horas);
			model.addAttribute("puestos",json);
			
		
			return "home";
		}else {
			model.addAttribute("usuario",null);
			return"redirect:/";
		}
		

	}
	
	public int[] calcularHorasLibres(JSONArray json) {
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
		
		return horas;
	}
	
		
	//Lee JSON de una url
	public static JSONArray getJson(URL url) {
	    String json;
		try {
			json = IOUtils.toString(url, StandardCharsets.UTF_8);
			return new JSONArray(json);
		} catch (IOException e) {
			System.err.println("Error parseando el JSON");
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
					urljson = new URL(BASEURL+"/usuarios/todos");
				} catch (MalformedURLException e) {
					
					model.addAttribute("error", ERRORJSON);
					e.printStackTrace();
					return "error";
				}
				
				JSONArray json=getJson(urljson);
				
				System.out.println(usuario.getIdentificacion()+" accediendo a la información de los usuarios");
				
				model.addAttribute("lista_usuarios",json);
				model.addAttribute("usuario",usuario);
				
				return("usuarios");
			}
			
			return("redirect:/");
		}
		
		
		
		//puestos
		@GetMapping("/ver_puestos") 
		public String moverPuestos(Model model, HttpSession session) {
			
			Usuario usuario= (Usuario) session.getAttribute("usuario");
			if(usuario!=null && !usuario.getIdentificacion().isBlank()) {
				model.addAttribute("usuario",usuario);

				
				URL urljson;
				try {
					urljson = new URL(BASEURL+"/puestos/todos");
				} catch (MalformedURLException e) {

					model.addAttribute("error", ERRORJSON);
					e.printStackTrace();
					return "error";
				}
				
				JSONArray json=getJson(urljson);

				
				
				
				System.out.println("usuario entra: "+usuario.getIdentificacion());
				model.addAttribute("puestos",json);
				
				System.out.println(usuario.getIdentificacion()+" accediendo a la información de los puestos");
				
				
				model.addAttribute("nuevo_puesto", new Puesto());
				
			
				return "puestos";
			}else {
				model.addAttribute("usuario",null);
				return"redirect:/";
			}
			

		}
		
		
		
			//ADmin
			@GetMapping("/admin") 
			public String moverAdmin(Model model, HttpSession session) {
				
				Usuario usuario= (Usuario) session.getAttribute("usuario");
				if(usuario!=null && !usuario.getIdentificacion().isBlank()) {
					model.addAttribute("usuario",usuario);

					
					URL urljson;
					try {
						urljson = new URL(BASEURL+"/puestos/todos");
					} catch (MalformedURLException e) {

						model.addAttribute("error", ERRORJSON);
						e.printStackTrace();
						return "error";
					}
					
					JSONArray json=getJson(urljson);

					model.addAttribute("puestos",json);
					
					
					
					try {
						urljson = new URL(BASEURL+"/usuarios/todos");
					} catch (MalformedURLException e) {

						model.addAttribute("error", ERRORJSON);
						e.printStackTrace();
						return "error";
					}
					
					json=getJson(urljson);

					model.addAttribute("usuarios",json);
					

					
					
				
					return "admin";
				}else {
					model.addAttribute("usuario",null);
					return"redirect:/";
				}
				

			}
		
}
