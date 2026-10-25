package com.dsai.travelflux.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origen;
    private String destino;
    private String paisOrigen;
    private String paisDestino;
    private String duracion;
    private String tipo;
    private String aerolineas;
    private String codigoOrigen;
    private String codigoDestino;
    private String imagenUrl;

    public Vuelo() {
    }

    public Vuelo(String origen, String destino,
                 String paisOrigen, String paisDestino,
                 String duracion, String tipo, String aerolineas,
                 String codigoOrigen, String codigoDestino,
                 String imagenUrl) {
        this.origen = origen;
        this.destino = destino;
        this.paisOrigen = paisOrigen;
        this.paisDestino = paisDestino;
        this.duracion = duracion;
        this.tipo = tipo;
        this.aerolineas = aerolineas;
        this.codigoOrigen = codigoOrigen;
        this.codigoDestino = codigoDestino;
        this.imagenUrl = imagenUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAerolineas() {
        return aerolineas;
    }

    public void setAerolineas(String aerolineas) {
        this.aerolineas = aerolineas;
    }

    public String getCodigoOrigen() {
        return codigoOrigen;
    }

    public void setCodigoOrigen(String codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public String getCodigoDestino() {
        return codigoDestino;
    }

    public void setCodigoDestino(String codigoDestino) {
        this.codigoDestino = codigoDestino;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}