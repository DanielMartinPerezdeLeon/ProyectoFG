package com.booking.entity;

import java.util.Arrays;
import java.util.Base64;



import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class Usuario {

	
	@Column(name = "nombre")
	//@JsonProperty("nombre")
	private String nombre;
	
	@Column(name = "apellidos")
	//@JsonProperty("apellidos")
	private String apellidos;
	
	@Id
	@Column(name = "identificacion")
	//@JsonProperty("identificación")
	private String identificacion;	//valor con el que iniciar sesión
	
	//@Column(name = "contrasena")
	//private String contrasena;	//contraseña final
	
	@Column(name = "contrasena")
	//@JsonProperty("contrasena")
	private byte[] contrasena;	//Contraseña encriptada para no mostrar la original+ (Base64)
	
	@Column(name = "rol")
	private int rol; //1,usuario; 2,manager; 3,admin; 0,no-aceptado
	
	
	public Usuario(){
		
	}
	
	public Usuario(String nombre, String apellido, String identificacion, String contrasena,int rol){
		super();
		
		if(nombre.length()<=0 || nombre.length()>50) {System.err.println("nombre tamaño incorrecto");}
		if(apellidos.length()<=0 || apellidos.length()>90) {System.err.println("apellido tamaño incorrecto ");}
		if(identificacion.length()<=0 || identificacion.length()>45) {System.err.println("identificacion tamaño incorrecto ");}
		if(contrasena.length()<=0 || contrasena.length()>100) {System.err.println("contraseña tamaño incorrecta  ");}
		

		this.nombre=nombre;
		this.apellidos=apellido;
		this.identificacion=identificacion;
		this.contrasena=Base64.getEncoder().encode(contrasena.getBytes());
	}
	
	public Usuario(String identificacion, String contrasena) {
		super();
		this.identificacion=identificacion;
		this.contrasena=Base64.getEncoder().encode(contrasena.getBytes());
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getContrasena() {
		return contrasena;
	}

	public void setContrasena(byte[] contrasena) {
		this.contrasena = contrasena;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", identificacion="
				+ identificacion + ", contrasenaCodificada=" + Arrays.toString(contrasena) + "]";
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}
	
	
	
	
	
	
	
	
}
