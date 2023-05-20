package com.booking.entity;

import jakarta.persistence.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Entity
@Table(name = "puestos")
public class Puesto {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "estado")
    private boolean estado;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "reservas")
    private String reservas;



    public Puesto() {
        // Default constructor required by JPA
    }

    public Puesto(int id, boolean estado, String tipo) {
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        setReservasDefault();
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReservas() {
        return reservas;
    }

    public void setReservas(String reservas) {
        this.reservas = reservas;
    }
    
    public void setReservasDefault() {
    	JSONObject jsonObject = new JSONObject();
    	JSONArray reservasArray = new JSONArray();

    	for (int i = 0; i < 24; i++) {
    	    JSONArray innerArray = new JSONArray();
    	    innerArray.put(i);
    	    innerArray.put("");
    	    reservasArray.put(innerArray);
    	}

    	jsonObject.put("reservas", reservasArray);
    	
    	this.reservas=jsonObject.toString();
    }
    
}
