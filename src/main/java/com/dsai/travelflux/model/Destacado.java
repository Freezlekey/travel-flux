package com.dsai.travelflux.model;

public class Destacado {

    private Long id;
    private String tipo;
    private String titulo;
    private String ubicacion;
    private String imagenUrl;
    private String url;

    public Destacado() {
    }

    public Destacado(Long id, String tipo, String titulo,
                     String ubicacion, String imagenUrl, String url) {
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.imagenUrl = imagenUrl;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public String getUrl() {
        return url;
    }
}