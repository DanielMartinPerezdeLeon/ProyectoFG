package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //Valor ID autogenerado por spring
	
	@Column(name = "nombre")
	@JsonProperty("nombre")
	private String nombre;
	
	@Column(name = "apellidos")
	@JsonProperty("apellidos")
	private String apellidos;
	
	@Column(name = "identificacion")
	@JsonProperty("identificación")
	private String identificacion;	//valor con el que iniciar sesión
	
	//@Column(name = "contrasena")
	//private String contrasena;	//contraseña final
	
	@Column(name = "contrasena")
	@JsonProperty("contrasena")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	
	
	
	
}
