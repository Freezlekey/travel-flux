package com.dsai.travelflux.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ubicacion;
    private int estrellas;
    private String descripcion;
    private String historia;
    private String imagenUrl;
    private String popularidad;
    private String urlOficial;

    public Hotel() {
    }

    public Hotel(String nombre, String ubicacion, int estrellas,
                 String descripcion, String historia, String imagenUrl,
                 String popularidad, String urlOficial) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.estrellas = estrellas;
        this.descripcion = descripcion;
        this.historia = historia;
        this.imagenUrl = imagenUrl;
        this.popularidad = popularidad;
        this.urlOficial = urlOficial;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(String popularidad) {
        this.popularidad = popularidad;
    }

    public String getUrlOficial() {
        return urlOficial;
    }

    public void setUrlOficial(String urlOficial) {
        this.urlOficial = urlOficial;
    }
}