package com.booking.entity;

import jakarta.persistence.*;


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
    
    public Puesto(int id, boolean estado, String tipo, String reservas) {
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        this.reservas=reservas;
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
    	String def_reservas="[{\"id\": 8, \"detalle\": \"\"}, {\"id\": 9, \"detalle\": \"\"}, {\"id\": 10, \"detalle\": \"\"}, {\"id\": 11, \"detalle\": \"\"}, {\"id\": 12, \"detalle\": \"\"}, {\"id\": 13, \"detalle\": \"\"}, {\"id\": 14, \"detalle\": \"\"}, {\"id\": 15, \"detalle\": \"\"}]";
    	this.reservas=def_reservas;
    }
    
}
