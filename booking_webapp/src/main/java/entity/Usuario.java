package entity;

import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //Valor ID autogenerado por spring
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellidos")
	private String apellidos;
	
	@Column(name = "identificacion")
	private String identificacion;	//valor con el que iniciar sesión
	
	//@Column(name = "contrasena")
	//private String contrasena;	//contraseña final
	
	@Column(name = "contrasena codificada")
	private Byte[] contrasenaCodificada;	//Contraseña encriptada para no mostrar la original
	
	
	Usuario(){
		
	}
	
	Usuario(String nombre, String apellido, String identificacion, Byte[] contrasena){
		super();
		this.nombre=nombre;
		this.apellidos=apellido;
		this.identificacion=identificacion;
		this.contrasenaCodificada=contrasena;
	}
	
	
}
